package com.athornatus.bussiness.util.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
    protected String error;
    protected int status;
    protected LocalDateTime timestamp;
    protected String details;
}