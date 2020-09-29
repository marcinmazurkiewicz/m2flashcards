package dev.mazurkiewicz.m2flashcards.exception;

import dev.mazurkiewicz.m2flashcards.exception.validation.ErrorType;
import lombok.Getter;

import java.util.Map;

@Getter
public abstract class CustomRuntimeException extends RuntimeException {

    private Map<String, String> errors;
    private final ErrorType errorType;

    public CustomRuntimeException(String msg, ErrorType errorType, Map<String, String> errors) {
        super(msg);
        this.errorType = errorType;
        this.errors = errors;
    }

}
