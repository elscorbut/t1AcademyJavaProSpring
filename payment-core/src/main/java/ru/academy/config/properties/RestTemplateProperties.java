package ru.academy.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RestTemplateProperties {
    private String uri;
    private Duration connectTimeout;
    private Duration readTimeout;
}
