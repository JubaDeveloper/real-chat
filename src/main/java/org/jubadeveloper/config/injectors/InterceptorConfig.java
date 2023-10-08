package org.jubadeveloper.config.injectors;

import org.jubadeveloper.actors.interceptors.AuthenticatedPathInterceptor;
import org.jubadeveloper.core.usecases.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthService authService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticatedPathInterceptor(authService))
                .addPathPatterns("/panel/**");
    }
}
