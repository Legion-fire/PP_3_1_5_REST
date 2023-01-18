package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    @Transactional
    public boolean add(Role role) {
        roleRepository.add(role);
        return true;
    }

    @Override
    @Transactional
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleRepository.findByIdRoles(roles);
    }

    @Override
    @Transactional
    public Set<Role> findAll() {
        return roleRepository.findAll();
    }

}
