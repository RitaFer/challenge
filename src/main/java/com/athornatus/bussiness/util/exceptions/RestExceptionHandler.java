package com.athornatus.bussiness.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.time.ZoneId;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("BAD REQUEST")
                        .details(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("NOT FOUND")
                        .details(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

}
