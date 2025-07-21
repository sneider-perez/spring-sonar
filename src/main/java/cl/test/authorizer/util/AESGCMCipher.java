package cl.test.authorizer.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESGCMCipher {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;

    private final SecretKey secretKey;

    public AESGCMCipher(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String encrypt(String plainText) {
        try {
            return doEncrypt(plainText);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while encrypting");
        }
    }

    public String doEncrypt(String plainText) throws Exception {
        byte[] iv = generateIV();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        byte[] ivAndEncrypted = new byte[GCM_IV_LENGTH + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, GCM_IV_LENGTH);
        System.arraycopy(encrypted, 0, ivAndEncrypted, GCM_IV_LENGTH, encrypted.length);

        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    public String decrypt(String base64IvAndEncrypted) {
        try {
            return doDecrypt(base64IvAndEncrypted);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while decrypting", e);
        }
    }

    public String doDecrypt(String base64IvAndEncrypted) throws Exception {
        byte[] ivAndEncrypted = Base64.getDecoder().decode(base64IvAndEncrypted);

        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] encrypted = new byte[ivAndEncrypted.length - GCM_IV_LENGTH];

        System.arraycopy(ivAndEncrypted, 0, iv, 0, GCM_IV_LENGTH);
        System.arraycopy(ivAndEncrypted, GCM_IV_LENGTH, encrypted, 0, encrypted.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted);
    }

    private byte[] generateIV() {
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

}
