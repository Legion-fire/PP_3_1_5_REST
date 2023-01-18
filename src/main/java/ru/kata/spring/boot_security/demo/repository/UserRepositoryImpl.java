package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User find(String email) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select distinct u from User u left join fetch u.roles ", User.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }
}
