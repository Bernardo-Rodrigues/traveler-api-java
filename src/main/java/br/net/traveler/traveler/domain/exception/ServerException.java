package br.net.traveler.traveler.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ServerException(String reason) {
        super(reason);
    }

}
