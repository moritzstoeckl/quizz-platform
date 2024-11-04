package com.quiz_platform.authenticationdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
//@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * false if user has to be deactivated but should not be deleted.
     */
    @Column(nullable = false)
    private boolean isDeactivated;

    public User(String username, String password, boolean isDeactivated) {
        this.username = username;
        this.password = password;
        this.isDeactivated = isDeactivated;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeactivated() {
        return isDeactivated;
    }

    public void setDeactivated(boolean deactivated) {
        isDeactivated = deactivated;
    }
}

