package ru.academy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "userdb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {}

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + "]";
    }
}
