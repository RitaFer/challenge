package com.athornatus.bussiness.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.ZoneId;

@RestControllerAdvice(basePackages = {"com.athornatus.api.controllers"})
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime())
                        .status(HttpStatus.NOT_FOUND)
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime())
                        .status(HttpStatus.BAD_REQUEST)
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
