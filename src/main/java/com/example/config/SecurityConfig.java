package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/logout").permitAll()
                .requestMatchers("/api/auth/session-status").permitAll()
                .requestMatchers("/api/auth/**").permitAll()

                .requestMatchers("/api/client/**").permitAll()
                .requestMatchers("/api/visitor-driver/**").permitAll()
                .requestMatchers("/api/selected-driver/**").permitAll()
                .requestMatchers("/api/drivers").permitAll()
                .requestMatchers("/api/drivers/**").permitAll()

                .requestMatchers("/api/driver-documents/upload/**").permitAll()
                .requestMatchers("/driver-documents/upload/**").permitAll()

                .requestMatchers("/api/driver-verification/**").permitAll()
                .requestMatchers("/api/gdc/**").permitAll()

                .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
            )

            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable())

            // ⭐ Correct placement of SessionAuthFilter
            .addFilterBefore(new SessionAuthFilter(), AnonymousAuthenticationFilter.class)

            // ⭐ Return 401 for expired session instead of redirecting
            .exceptionHandling(e -> e
                .authenticationEntryPoint((req, res, ex) -> res.sendError(401, "Unauthorized"))
            );

        return http.build();
    }

    // ---------------- COOKIE CONFIG FOR PRODUCTION ----------------
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();

        serializer.setSameSite("None");  
        serializer.setUseSecureCookie(true);
        serializer.setCookiePath("/");

        return serializer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ---------------- CORS CONFIG ----------------
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        config.setAllowedOrigins(List.of(
            "https://lambent-yeot-32a75f.netlify.app"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Set-Cookie"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
