package com.cloud.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class WebsocketClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketClientApplication.class, args);
    }

    /**
     * 使用 Spring Java Config 方式声明 RestTemplate
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
