package cl.test.authorizer.service.impl;

import cl.test.authorizer.repository.CacheAuthRepository;
import cl.test.authorizer.service.JwksCacheService;
import com.nimbusds.jose.jwk.JWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwksCacheServiceImpl implements JwksCacheService {

    private static final String CACHE_KEY = "jwks::oauth-apigee";
    private static final long TTL_MINUTES = 1440;

    @Value("${jwks.oauth.endpoint}")
    private String jwksUrl;

    private final CacheAuthRepository cacheAuthRepository;
    private final HttpClient httpClient;

    @Override
    public JWKSet getJwkSet() throws ParseException {
        Optional<String> jwksJsonOpt = cacheAuthRepository.getValue(CACHE_KEY);

        if (jwksJsonOpt.isEmpty()) {
            String jwksJson = fetchJwksFromRemote();
            cacheAuthRepository.setValue(CACHE_KEY, jwksJson, TTL_MINUTES);
            jwksJsonOpt = Optional.of(jwksJson);
        }

        return JWKSet.parse(jwksJsonOpt.orElse(""));
    }

    @Override
    public void forceRefresh() {
        String jwksJson = fetchJwksFromRemote();
        cacheAuthRepository.setValue(CACHE_KEY, jwksJson, TTL_MINUTES);
    }

    private String fetchJwksFromRemote() {
        try {
            return tryFetchJwksFromRemote();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("500");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("500");
        }
    }

    private String tryFetchJwksFromRemote() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(jwksUrl))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        validateJwsResponse(response);
        return response.body();
    }

    private void validateJwsResponse(HttpResponse<String> response) {
        if (response.statusCode() != 200) {
            throw new RuntimeException("500");
        }
    }

}

