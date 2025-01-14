package com.example.fruit_api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {
    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String scope;
}
