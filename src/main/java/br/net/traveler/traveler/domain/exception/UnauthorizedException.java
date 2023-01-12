package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String reason, String errorCode) {
        super(reason);
    }
}
