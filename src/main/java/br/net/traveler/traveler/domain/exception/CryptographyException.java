package br.net.traveler.traveler.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CryptographyException extends RuntimeException{

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CryptographyException(String reason) {
        super(reason);
    }
}
