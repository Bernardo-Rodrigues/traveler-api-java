package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends DefaultException {
    private final String errorCode;


    public UnauthorizedException(String reason, String errorCode) {
        super(reason);
        this.errorCode = errorCode;
    }
}
