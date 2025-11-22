package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
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
            	    .requestMatchers("/api/auth/**").permitAll()
            	    .requestMatchers("/api/auth/login").permitAll()   // ✅ FIX 1: Explicit login allow
            	    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll() // ✅ FIX 2: Allow OPTIONS preflight

            	    .requestMatchers("/api/client/**").permitAll()
            	    .requestMatchers("/api/visitor-driver/**").permitAll()
            	    .requestMatchers("/api/selected-driver/**").permitAll()
            	    .requestMatchers("/api/drivers").permitAll()
            	    .requestMatchers("/api/drivers/licences/driver/**").permitAll()
            	    .requestMatchers("/api/drivers/sarpanch/driver/**").permitAll()
            	    .requestMatchers("/api/drivers/experience/driver/**").permitAll()
            	    .requestMatchers("/api/driver-documents/upload/**").permitAll()
            	    .requestMatchers("/driver-documents/upload/**").permitAll()
            	    .requestMatchers("/api/driver-verification/pending").permitAll()
            	    .requestMatchers("/api/driver-verification/documents/**").permitAll()
            	    .requestMatchers("/api/driver-verification/approve").permitAll()
            	    .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")
            	    .anyRequest().authenticated()
            	)

            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .addFilterBefore(new SessionAuthFilter(),
                org.springframework.security.web.authentication.AnonymousAuthenticationFilter.class);

        return http.build();
    }


    // ---------------- COOKIE CONFIG FOR PRODUCTION ----------------
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();

        serializer.setSameSite("None");  // REQUIRED for cross-site cookies
        serializer.setUseSecureCookie(true); // REQUIRED for Netlify HTTPS
        serializer.setCookiePath("/");

        // For future local testing:
        // serializer.setSameSite("Lax");
        // serializer.setUseSecureCookie(false);

        return serializer;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // ---------------- CORS CONFIG (PRODUCTION + LOCAL COMMENTED) ----------------
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
            "https://lambent-yeot-32a75f.netlify.app"   // LIVE FRONTEND

            // "http://localhost:5174"  // uncomment for local dev
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
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

