package br.net.traveler.traveler.services;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface CryptographyService {

    String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException;
    String decrypt(String property);
}
