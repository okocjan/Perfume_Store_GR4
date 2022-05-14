package com.example.pzespolowe.Models;

import javax.persistence.*;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "username_UNIQUE", columnList = "username", unique = true)
})
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}