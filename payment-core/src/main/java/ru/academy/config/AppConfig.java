package ru.academy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.academy.config.properties.IntegrationProperties;
import ru.academy.config.properties.RestTemplateProperties;

@Configuration
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class AppConfig {

    private final IntegrationProperties integrationProperties;

    @Bean
    public RestTemplate productClient(RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties properties = integrationProperties.getProductClient();

        return new RestTemplateBuilder()
                .rootUri(properties.getUri())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
