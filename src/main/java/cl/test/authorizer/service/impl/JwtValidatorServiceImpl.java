package cl.test.authorizer.service.impl;

import cl.test.authorizer.service.JwksCacheService;
import cl.test.authorizer.service.JwtValidatorService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtValidatorServiceImpl implements JwtValidatorService {

    @Value("${jwt.oauth.issuer}")
    private String issuer;

    private final JwksCacheService jwksCacheService;

    @Override
    public Optional<String> getUsername(String token) {
        try {
            JWTClaimsSet claimsSet = getClaims(token);
            validateClaims(claimsSet);
            return Optional.of(claimsSet.getSubject());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isValid(String token) {
        try {
            validateClaims(getClaims(token));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public JWTClaimsSet getClaims(String token) throws BadJOSEException, ParseException, JOSEException {
        JWKSource<SecurityContext> source = new ImmutableJWKSet<>(getJwkSet(token));

        ConfigurableJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
        JWSKeySelector<SecurityContext> keySelector =
                new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, source);

        processor.setJWSKeySelector(keySelector);

        return processor.process(token, null);
    }

    private void validateClaims(JWTClaimsSet claims) throws BadJWTException {
        validateExpiration(claims);
        validateIssuer(claims);
    }

    private void validateExpiration(JWTClaimsSet claims) throws BadJWTException {
        Date nowWithLeeway = new Date(System.currentTimeMillis() + 3000);
        if (claims.getExpirationTime() == null || claims.getExpirationTime().before(nowWithLeeway)) {
            throw new BadJWTException("El token ha expirado");
        }
    }

    private void validateIssuer(JWTClaimsSet claims) throws BadJWTException {
        if (!Objects.equals(claims.getIssuer(), issuer)) {
            throw new BadJWTException("Issuer iválido");
        }
    }

    private JWKSet getJwkSet(String token) throws ParseException {
        String kid = extractKid(token);
        JWKSet jwkSet = jwksCacheService.getJwkSet();

        if (!containsKid(jwkSet, kid)) {
            jwksCacheService.forceRefresh();
            jwkSet = jwksCacheService.getJwkSet();
        }

        return jwkSet;
    }

    private String extractKid(String token) throws ParseException {
        SignedJWT jwt = SignedJWT.parse(token);
        return jwt.getHeader().getKeyID();
    }

    private boolean containsKid(JWKSet jwkSet, String kid) {
        return jwkSet.getKeys().stream()
                .anyMatch(k -> Objects.equals(k.getKeyID(), kid));
    }

}
