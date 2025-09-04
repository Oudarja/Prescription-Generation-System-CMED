package com.example.PrescriptionGeneration.Config;

import com.example.PrescriptionGeneration.Security.JwtAuthenticationFilter;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Protect endpoints except /auth/**
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll() // allow register/login
                // allow Swagger UI and OpenAPI endpoints
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated() // protect everything else
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CORS configuration to allow React frontend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // allow React frontend URL
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); 
        // OPTIONS is included so that preflight requests are allowed.
        // preflight requests are sent by browsers to check if the actual request is safe to send.
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // allow all headers but can be restricted to specific headers if needed
        // safer to restrict to specific headers like "Authorization", "Content-Type"
        configuration.setAllowedHeaders(List.of("*"));
        // needed if sending cookies, optional for JWT
        configuration.setAllowCredentials(true); 
        // creates a container to hold URL-based CORS rules.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // apply this configuration to all endpoints
        source.registerCorsConfiguration("/**", configuration);
        // provides this source to Spring Security so that it knows which CORS 
        // rules to apply when handling requests.
        return source;
    }
}
