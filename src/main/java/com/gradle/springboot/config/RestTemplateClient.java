package com.gradle.springboot.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateClient {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1000* 30))
                .setReadTimeout(Duration.ofSeconds(1000* 30))
                .build();
    }
}
