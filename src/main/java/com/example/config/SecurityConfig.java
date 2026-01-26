package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

    private final SessionAuthFilter sessionAuthFilter;

    public SecurityConfig(SessionAuthFilter sessionAuthFilter) {
        this.sessionAuthFilter = sessionAuthFilter;
    }

    // ===================== PAYMENT APIs (NO SECURITY AT ALL) =====================
    @Bean
    @Order(1)
    public SecurityFilterChain paymentFilterChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/api/payments/**")
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .sessionManagement(session -> session.disable())
            .securityContext(context -> context.disable())
            .requestCache(cache -> cache.disable())
            .exceptionHandling(ex -> ex.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    // ===================== MAIN SECURITY =====================
    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/generalauth/**").permitAll()

                .requestMatchers("/api/client/**").permitAll()
                .requestMatchers("/api/visitor-driver/**").permitAll()
                .requestMatchers("/api/selected-driver/**").permitAll()
                .requestMatchers("/api/drivers/**").permitAll()
                .requestMatchers("/api/reports/drivers/**").permitAll()
                .requestMatchers("/api/driver-documents/upload/**").permitAll()
                .requestMatchers("/driver-documents/upload/**").permitAll()
                .requestMatchers("/api/driver-verification/**").permitAll()
                .requestMatchers("/api/gdc/**").permitAll()
                .requestMatchers("/api/payments/**").permitAll()
                .requestMatchers("/api/transporter/**").permitAll()
                .requestMatchers("/api/transporter").permitAll()
                .requestMatchers("/api/transporter-vehicle").permitAll()
                .requestMatchers("/api/transporter-vehicle/**").permitAll()
                .requestMatchers("/api/transporter-documents/**").permitAll()
                .requestMatchers("/api/transporter-documents").permitAll()
                .requestMatchers("/api/transporter-verification/**").permitAll()
                .requestMatchers("/api/transporter-verification").permitAll()
                .requestMatchers("/api/driver-request/create").permitAll()
                .requestMatchers("/api/driver-request/create/**").permitAll()
                .requestMatchers("/api/notifications/").permitAll()
                .requestMatchers("/api/notifications/admin/mark-read/").permitAll()
                .requestMatchers("/api/notifications/admin/mark-read/**").permitAll()
                .requestMatchers("/api/notifications/admin/**").permitAll()
                .requestMatchers("/api/notifications/admin/").permitAll()
                .requestMatchers("/api/wallets/**").permitAll()







                .requestMatchers("/api//api/transporter-gdc/generate").permitAll()
                .requestMatchers("/api//api/transporter-gdc/generate/**").permitAll()




                .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
            )

            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .addFilterBefore(sessionAuthFilter, AnonymousAuthenticationFilter.class)

            .exceptionHandling(e -> e
                .authenticationEntryPoint((req, res, ex) ->
                        res.sendError(401, "Unauthorized"))
            );

        return http.build();
    }

    // ===================== COOKIE =====================
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

    // ===================== CORS =====================
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
