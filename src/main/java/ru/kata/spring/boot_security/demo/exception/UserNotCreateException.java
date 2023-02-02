package ru.kata.spring.boot_security.demo.exception;

public class UserNotCreateException extends RuntimeException {
    public UserNotCreateException(String message) {
        super(message);
    }
}
