package com.example.demo.GitHub;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FeginConfiguration {
    //I tried to implement the Spring Security "OAuth2" security mechanism. However, I encountered an issue with its configuration.
    @Value("${github.api.key}")
    private String gitHubAccessToken;

    @Bean
    RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Authorization",
                        String.format("Bearer %s", gitHubAccessToken));
            }
        };
    }
}