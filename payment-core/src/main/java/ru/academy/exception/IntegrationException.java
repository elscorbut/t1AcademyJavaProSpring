package ru.academy.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class IntegrationException extends RuntimeException {

    private final String code;
    private final HttpStatusCode statusCode;

    public IntegrationException(String message, String code, HttpStatusCode statusCode) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
    }
}
