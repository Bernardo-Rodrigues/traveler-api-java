package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class CryptographyException extends RuntimeException{

    public CryptographyException(String reason, String errorCode) {
        super(reason);
    }
}
