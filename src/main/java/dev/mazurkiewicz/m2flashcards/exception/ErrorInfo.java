package dev.mazurkiewicz.m2flashcards.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorInfo {

    private ErrorType errorType;
    private String msg;

}
