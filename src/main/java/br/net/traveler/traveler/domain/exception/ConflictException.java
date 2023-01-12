package br.net.traveler.traveler.domain.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {

    public ConflictException(String reason, String errorCode) {
        super(reason);
    }

}
