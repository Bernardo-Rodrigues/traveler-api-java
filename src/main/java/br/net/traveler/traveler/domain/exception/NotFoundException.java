package br.net.traveler.traveler.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String reason) {
        super(reason);
    }

}
