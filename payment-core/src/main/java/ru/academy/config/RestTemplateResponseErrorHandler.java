package ru.academy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.academy.dto.ErrorResponseDto;
import ru.academy.exception.IntegrationException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpStatusCode statusCode = response.getStatusCode();
        ErrorResponseDto errorResponse = mapper.readValue(response.getBody(), ErrorResponseDto.class);

        if (statusCode.is5xxServerError()) {
            throw createIntegrationException(errorResponse, statusCode, "Server");
        }
        if (statusCode.is4xxClientError()) {
            throw createIntegrationException(errorResponse, statusCode, "Client");
        }

    }

    private IntegrationException createIntegrationException(ErrorResponseDto errorResponse, HttpStatusCode statusCode, String errorOccurredAt) {
        return new IntegrationException(String.format("%s error: %d - %s",
                errorOccurredAt, statusCode.value(), errorResponse.getMessage()), errorResponse.getCode(), statusCode);
    }
}
