package br.net.traveler.traveler.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(String reason) {
        super(reason);
    }

}
