package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    public Role() {
    }
    public Role(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getAuthority() {
        return getName();
    }


}
