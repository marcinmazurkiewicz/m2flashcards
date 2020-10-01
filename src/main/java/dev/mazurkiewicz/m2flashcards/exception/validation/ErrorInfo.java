package dev.mazurkiewicz.m2flashcards.exception.validation;

import dev.mazurkiewicz.m2flashcards.exception.validation.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorInfo {

    private ErrorType errorType;
    private String msg;

}
