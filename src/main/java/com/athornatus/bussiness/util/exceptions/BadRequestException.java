package com.athornatus.bussiness.util.exceptions;

public class BadRequestException extends RuntimeException {
    private final String message;
    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
