package com.test.wh.po;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username", length=50)
    private String username;
    @Column(name="email", length=50)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    public String getUsername() {
        return this.username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
