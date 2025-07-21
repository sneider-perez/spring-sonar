package cl.test.authorizer.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESKeyProvider {

    private static final String ALGORITHM = "AES";

    private AESKeyProvider() {
    }

    public static SecretKey fromString(String keyString) {
        if (validateSecret(keyString)) {
            throw new IllegalArgumentException("Secret key must be exactly 16 characters (128 bits)");
        }
        return new SecretKeySpec(keyString.getBytes(), ALGORITHM);
    }

    private static boolean validateSecret(String secret) {
        return secret == null || secret.length() != 16;
    }

}
