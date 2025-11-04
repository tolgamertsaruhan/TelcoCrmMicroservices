package com.etiya.catalogservice.configuration;

import com.etiya.common.configuration.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfig(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http);
        http.authorizeHttpRequests(req -> req.requestMatchers("/api/**").permitAll().anyRequest().authenticated());
        //http.authorizeHttpRequests(req -> req.anyRequest().authenticated());

        return http.build();
    }
}
