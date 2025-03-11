package ru.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.academy.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";
    private static final String NOT_ENOUGH_FUNDS = "NOT_ENOUGH_FUNDS";
    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                USER_NOT_FOUND,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                PRODUCT_NOT_FOUND,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<ErrorResponseDto> handleNotEnoughFundsException(NotEnoughFundsException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                NOT_ENOUGH_FUNDS,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
