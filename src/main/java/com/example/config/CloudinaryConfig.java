package com.example.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name", "dckc0rk0n",
                "api_key", "677616653359811",
                "api_secret", "g8ikY1I07O9GNsFR_aw9alTWr4s",



                "secure", "true"
        );
        return new Cloudinary(config);
    }
}
