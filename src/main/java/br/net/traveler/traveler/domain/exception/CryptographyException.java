package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class CryptographyException extends DefaultException{

    private final String errorCode;


    public CryptographyException(String reason, String errorCode) {
        super(reason);
        this.errorCode = errorCode;
    }
}
