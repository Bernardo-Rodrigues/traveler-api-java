package br.net.traveler.traveler.services;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface CryptographyService {

    String encrypt(String property);
    String decrypt(String property);

    Boolean matches(String property, String encryptedProperty);
}
