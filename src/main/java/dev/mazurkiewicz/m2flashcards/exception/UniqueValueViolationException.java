package dev.mazurkiewicz.m2flashcards.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UniqueValueViolationException extends RuntimeException {

    private Map<String, String> errors;
    private final ErrorType errorType = ErrorType.UNIQUE;

    public UniqueValueViolationException(String msg, Map<String, String> errors) {
        super(msg);
        this.errors = errors;
    }

    public UniqueValueViolationException(String msg, String field, String error) {
        super(msg);
        errors = new HashMap<>();
        errors.put(field, error);
    }

}
