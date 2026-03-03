package com.example.spring_data_redis_cache.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalHandlerException {

    //handle dto, controller error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> globalError(MethodArgumentNotValidException exception){
        HashMap<String, String> error = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
