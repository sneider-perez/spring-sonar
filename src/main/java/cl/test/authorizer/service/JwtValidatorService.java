package cl.test.authorizer.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;
import java.util.Optional;

public interface JwtValidatorService {

    JWTClaimsSet getClaims(String token) throws BadJOSEException, ParseException, JOSEException, InterruptedException;

    Optional<String> getUsername(String token);

    boolean isValid(String token);

}
