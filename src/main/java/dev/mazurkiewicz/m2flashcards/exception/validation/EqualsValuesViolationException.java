package dev.mazurkiewicz.m2flashcards.exception.validation;

import dev.mazurkiewicz.m2flashcards.exception.CustomRuntimeException;

import java.util.Map;

public class EqualsValuesViolationException extends CustomRuntimeException {

    public EqualsValuesViolationException(String msg, Map<String, String> errors) {
        super(msg, ErrorType.NOT_MATCH, errors);
    }
}
