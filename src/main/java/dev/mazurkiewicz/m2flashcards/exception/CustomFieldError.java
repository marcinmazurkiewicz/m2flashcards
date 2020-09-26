package dev.mazurkiewicz.m2flashcards.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
@Getter
public class CustomFieldError {
    private HttpStatus status;
    private String message;
    private Map<String, ErrorInfo> errors;

    public CustomFieldError(HttpStatus status, String message, Map<String, ErrorInfo> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public CustomFieldError(HttpStatus status, String message, String field, ErrorInfo error) {
        this.status = status;
        this.message = message;
        errors = new HashMap<>();
        errors.put(field, error);
    }
}

