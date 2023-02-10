package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository  {
    User findById(Long id);
    User find(String email);
    List<User> findAll();
    void deleteById(Long id);
    void save(User user);
    void update(User user);
}
