package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleRepository {
    Set<Role> findAll();
    boolean add(Role role);
    Set<Role> findByIdRoles(List<Long> roles);

}
