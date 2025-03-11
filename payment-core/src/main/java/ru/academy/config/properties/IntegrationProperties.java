package ru.academy.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("integration.clients")
public class IntegrationProperties {

    private final RestTemplateProperties productClient;
}
