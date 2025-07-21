package cl.test.authorizer.service;

import com.nimbusds.jose.jwk.JWKSet;

import java.text.ParseException;

public interface JwksCacheService {

    JWKSet getJwkSet() throws ParseException;

    void forceRefresh();

}

