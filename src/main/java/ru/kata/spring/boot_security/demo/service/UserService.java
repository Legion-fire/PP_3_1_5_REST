package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String name);
    List<User> findAll();
    void deleteById(Long id);
    void save(User user);
    void update(User user, Long id);
}
