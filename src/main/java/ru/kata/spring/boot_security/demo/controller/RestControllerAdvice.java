package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kata.spring.boot_security.demo.exception.UserErrorResponse;
import ru.kata.spring.boot_security.demo.exception.UserNotCreateException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(NoSuchElementException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage()
        );
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreateException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage()
        );
        return ResponseEntity.ok(response);
    }
}
