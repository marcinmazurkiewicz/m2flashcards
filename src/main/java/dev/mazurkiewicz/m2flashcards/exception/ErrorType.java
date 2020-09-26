package dev.mazurkiewicz.m2flashcards.exception;

import java.util.Arrays;

public enum ErrorType {
    NOT_EMPTY("NotEmpty"),
    NOT_NULL("NotNull"),
    UNIQUE("Unique"),
    UNKNOWN("");

    private final String codeName;

    ErrorType(String codeName) {
        this.codeName = codeName;
    }

    public static ErrorType valueOfCode(String codeName) {
        return Arrays.stream(ErrorType.values())
                .filter(errorType -> codeName.equals(errorType.codeName))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
