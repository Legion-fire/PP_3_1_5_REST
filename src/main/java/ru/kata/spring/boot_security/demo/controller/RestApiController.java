package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.UserErrorResponse;
import ru.kata.spring.boot_security.demo.exception.UserNotCreateException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/admin")
public class RestApiController {

    private final UserService userService;
    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping("/newAddUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public void pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> userSaveEdit(@RequestBody User user, @PathVariable("id") Long id) {
    user.setId(id);
    String oldPassword = userService.findById(id).getPassword();
    user.setPassword(oldPassword);
    userService.save(user);
    return ResponseEntity.ok(user);
}

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(NoSuchElementException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreateException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
