package com.example.admission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000")); // Дозвіл для React
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()       // Вхід і реєстрація
                        .requestMatchers("/faculties/**").permitAll()  // Список факультетів
                        .requestMatchers("/applicants/**").permitAll() // <--- ОСЬ ЦЬОГО НЕ ВИСТАЧАЛО!
                        .requestMatchers("/actuator/**").permitAll()   // Моніторинг Prometheus
                        .anyRequest().authenticated()                  // Все інше закрито
                );

        return http.build();
    }
}