package ru.kata.spring.boot_security.demo.exception;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class UserErrorResponse {
    private String message;
    public UserErrorResponse() {
    }
    public UserErrorResponse(String message) {
        this.message = message;
    }
}
