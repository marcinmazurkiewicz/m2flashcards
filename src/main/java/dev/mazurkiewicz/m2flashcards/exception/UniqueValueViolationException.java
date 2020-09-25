package dev.mazurkiewicz.m2flashcards.exception;

import java.util.HashMap;
import java.util.Map;

public class UniqueValueViolationException extends RuntimeException {

    private Map<String, String> errors;

    public UniqueValueViolationException(String msg, Map<String, String> errors) {
        super(msg);
        this.errors = errors;
    }

    public UniqueValueViolationException(String msg, String field, String error) {
        super(msg);
        errors = new HashMap<>();
        errors.put(field, error);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
