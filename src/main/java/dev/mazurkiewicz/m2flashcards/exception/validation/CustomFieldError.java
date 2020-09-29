package dev.mazurkiewicz.m2flashcards.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
@Getter
@AllArgsConstructor
public class CustomFieldError {
    private HttpStatus status;
    private String message;
    private Map<String, ErrorInfo> errors;

}

