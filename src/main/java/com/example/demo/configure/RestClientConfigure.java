package com.example.demo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class RestClientConfigure {
    
    @Bean
    RestClient restClient() {
        return RestClient.builder().build();
    }
}