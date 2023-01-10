package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class ConflictException extends DefaultException {

    private final String errorCode;


    public ConflictException(String reason, String errorCode) {
        super(reason);
        this.errorCode = errorCode;
    }

}
