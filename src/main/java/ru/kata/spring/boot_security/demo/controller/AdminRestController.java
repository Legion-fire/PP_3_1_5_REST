package ru.kata.spring.boot_security.demo.controller;

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
@RequestMapping("api/admin/users")
public class AdminRestController {

    private final UserService userService;
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping("/newAddUser")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") Long id) {
    userService.update(user, id);
    return ResponseEntity.ok(user);
}

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
