package com.example.ecommerce.serviceImpl;

import com.example.ecommerce.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    private RestTemplate restTemplate;

    TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean validatToken(String tokenValue) {

        UserDto userDto = restTemplate.postForObject("http://localhost:8080/user/validate/" + tokenValue, null, UserDto.class);

        if (userDto != null) {
            return true;
        }

        return false;
    }

}
