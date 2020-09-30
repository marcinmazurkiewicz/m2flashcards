package dev.mazurkiewicz.m2flashcards.exception.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ErrorType {
    EMPTY(List.of("NotEmpty", "NotNull", "NotBlank")),
    NOT_UNIQUE(List.of("UniqueMail")),
    NOT_MATCH(List.of("FieldMatch")),
    MAX(List.of("DecimalMax", "Max")),
    MIN(List.of("DecimalMin", "Min")),
    UNKNOWN(List.of());

    private final List<String> codeNames;

    ErrorType(List<String > codeNames) {
        this.codeNames = codeNames;
    }

    public static ErrorType valueOfCode(String codeName) {

        return Arrays.stream(ErrorType.values())
                .filter(errorType -> errorType.codeNames.contains(codeName))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
