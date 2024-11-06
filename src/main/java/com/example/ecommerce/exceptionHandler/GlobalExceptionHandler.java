package com.example.ecommerce.exceptionHandler;

import com.example.ecommerce.dto.Response;
import com.example.ecommerce.models.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeExceptionService.class)
    public ResponseEntity<ExceptionHandlerDTO> handleCustomException(RuntimeExceptionService ex) {
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(ex.getMessage(), "1234");
        return new ResponseEntity<>(exceptionHandlerDTO, HttpStatus.NOT_FOUND);
    }

}
