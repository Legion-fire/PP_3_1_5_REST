package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository  {
    User findById(Long id);
    User find(String email);
    List<User> findAll();
    void deleteById(Long id);
    void save(User user);
}
