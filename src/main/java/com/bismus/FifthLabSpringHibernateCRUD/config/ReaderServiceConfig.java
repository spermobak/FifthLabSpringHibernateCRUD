package com.bismus.FifthLabSpringHibernateCRUD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class ReaderServiceConfig {
    @Bean
    InputStream inputStream() {
        return System.in;
    }

}