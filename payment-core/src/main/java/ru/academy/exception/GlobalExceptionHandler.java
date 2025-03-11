package ru.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.academy.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ErrorResponseDto> handleIntegrationException(IntegrationException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ex.getCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnknownError(Exception ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                UNKNOWN_ERROR,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
