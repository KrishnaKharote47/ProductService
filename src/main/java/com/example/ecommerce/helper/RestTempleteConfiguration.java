package com.example.ecommerce.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTempleteConfiguration {

    @Bean
    public RestTemplate getRestTemplete() {
        RestTemplate restTemplate = new RestTemplate();
//        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
        return restTemplate;
    }
}
