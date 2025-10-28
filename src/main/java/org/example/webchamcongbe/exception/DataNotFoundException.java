package org.example.webchamcongbe.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super("DATA_NOT_FOUND_EXCEPTION");
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

