package org.jubadeveloper.config.injectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class ConfigInjector {
    @Bean
    org.jubadeveloper.core.ports.auth.Authenticator authenticator (@Value("${properties.private_key}") String privateKey)
            throws UnsupportedEncodingException {
        return new org.jubadeveloper.adapters.auth.Authenticator(privateKey);
    }
}