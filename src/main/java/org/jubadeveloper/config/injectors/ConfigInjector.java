package org.jubadeveloper.config.injectors;

import org.jubadeveloper.adapters.auth.AuthenticatorAdapter;
import org.jubadeveloper.core.ports.auth.AuthenticatorPort;
import org.jubadeveloper.core.ports.socket.SocketIoPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class ConfigInjector {
    @Bean
    AuthenticatorPort authenticator (@Value("${properties.private_key}") String privateKey)
            throws UnsupportedEncodingException {
        return new AuthenticatorAdapter(privateKey);
    }

    @Bean
    SocketIoPort socketIoPort () {
        return null;
    }
}