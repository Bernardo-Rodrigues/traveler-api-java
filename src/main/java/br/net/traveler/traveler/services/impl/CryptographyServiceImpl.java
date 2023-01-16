package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.exception.CryptographyException;
import br.net.traveler.traveler.services.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class CryptographyServiceImpl implements CryptographyService {

    @Autowired
    Environment environment;

    @Override
    public String encrypt(String property) {
        try {
            SecretKeySpec key = createKey();
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.ENCRYPT_MODE, key);
            AlgorithmParameters parameters = pbeCipher.getParameters();
            IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
            byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
            byte[] iv = ivParameterSpec.getIV();
            return base64Encode(iv) + ":" + base64Encode(cryptoText);
        } catch (Exception e){
            throw new CryptographyException("Error encrypting password");
        }
    }

    @Override
    public String decrypt(String string) {
        try {
            SecretKeySpec key = createKey();
            String iv = string.split(":")[0];
            String property = string.split(":")[1];
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
        } catch (Exception e){
            throw new CryptographyException("Error decrypting password");
        }
    }

    @Override
    public Boolean matches(String property, String encryptedProperty) {
        return property.equals(decrypt(encryptedProperty));
    }

    private SecretKeySpec createKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = environment.getProperty("password");
        if (password == null) {
            throw new IllegalArgumentException("No existing password in properties");
        }

        byte[] salt = new String("12345678").getBytes();

        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = createSecretKey(password.toCharArray(),
                salt, iterationCount, keyLength);

        return key;
    }

    private SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] base64Decode(String property) {
        return Base64.getDecoder().decode(property);
    }
}