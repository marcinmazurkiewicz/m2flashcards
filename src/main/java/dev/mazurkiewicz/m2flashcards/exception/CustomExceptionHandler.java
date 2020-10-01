package dev.mazurkiewicz.m2flashcards.exception;

import dev.mazurkiewicz.m2flashcards.exception.validation.CustomFieldError;
import dev.mazurkiewicz.m2flashcards.exception.validation.ErrorInfo;
import dev.mazurkiewicz.m2flashcards.exception.validation.ErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, ErrorInfo> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(
                    error.getField(),
                    new ErrorInfo(ErrorType.valueOfCode(error.getCode()), error.getDefaultMessage())
            );
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(
                    error.getObjectName(),
                    new ErrorInfo(ErrorType.valueOfCode(error.getCode()), error.getDefaultMessage())
            );
        }

        CustomFieldError fieldError =
                new CustomFieldError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return handleExceptionInternal(ex, fieldError, headers, fieldError.getStatus(), request);
    }
}
