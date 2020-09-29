package dev.mazurkiewicz.m2flashcards.exception.validation;

import java.util.Arrays;

public enum ErrorType {
    EMPTY("NotEmpty"),
    NULL("NotNull"),
    NOT_UNIQUE_MAIL("UniqueMail"),
    NOT_MATCH("FieldMatch"),
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
