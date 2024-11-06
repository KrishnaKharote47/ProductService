package com.example.ecommerce.exceptionHandler;


import com.example.ecommerce.models.ExceptionHandlerDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
public class CustomExceptionService extends Exception {
  public CustomExceptionService(String s) {
        super(s);
    }

}
