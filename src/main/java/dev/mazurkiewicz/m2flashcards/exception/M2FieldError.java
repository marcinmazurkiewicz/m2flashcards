package dev.mazurkiewicz.m2flashcards.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class M2FieldError {
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public M2FieldError(HttpStatus status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public M2FieldError(HttpStatus status, String message, String field, String error) {
        this.status = status;
        this.message = message;
        errors = new HashMap<>();
        errors.put(field, error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
