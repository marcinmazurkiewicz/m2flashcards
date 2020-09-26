package dev.mazurkiewicz.m2flashcards.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
                new CustomFieldError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, fieldError, headers, fieldError.getStatus(), request);
    }

    @ExceptionHandler({UniqueValueViolationException.class})
    public ResponseEntity<Object> handleUserEmailInUse(UniqueValueViolationException ex) {
        Map<String, ErrorInfo> errors = ex.getErrors()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> new ErrorInfo(ex.getErrorType(), entry.getValue())
                ));

        CustomFieldError fieldError =
                new CustomFieldError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(
                fieldError, new HttpHeaders(), fieldError.getStatus());
    }

}
