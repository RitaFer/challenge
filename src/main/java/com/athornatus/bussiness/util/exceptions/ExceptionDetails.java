package com.athornatus.bussiness.util.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
    private HttpStatus status;
    private String error;
    protected LocalDateTime timestamp;
}