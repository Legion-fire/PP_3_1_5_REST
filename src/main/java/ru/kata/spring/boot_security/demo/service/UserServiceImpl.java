package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,  @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
//        User user = new User();
//        Optional<User> optional = userRepository.findById(id);
//        if(optional.isPresent()) {
//            user = optional.get();
//        }
//        return user;
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.find(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public void update(User user, Long id) {
        String password  = findById(id).getPassword();
        if (user.getPassword().equals(password)) {
            user.setPassword(password);
            userRepository.save(user);
        } else {
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.update(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.find(username);
    }
}
