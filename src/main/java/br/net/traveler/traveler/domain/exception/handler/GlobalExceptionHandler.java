package br.net.traveler.traveler.domain.exception.handler;

import java.time.Instant;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.CryptographyException;
import br.net.traveler.traveler.domain.exception.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<StandardException> conflict(UnauthorizedException e, HttpServletRequest request) {
        String error = "Information already exists";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardException err = new StandardException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CryptographyException.class)
    public ResponseEntity<StandardException> cryptography(UnauthorizedException e, HttpServletRequest request) {
        String error = "Error in cryptography";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardException err = new StandardException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardException> unauthorizedAccess(UnauthorizedException e, HttpServletRequest request) {
        String error = "Unauthorized access";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardException err = new StandardException(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
