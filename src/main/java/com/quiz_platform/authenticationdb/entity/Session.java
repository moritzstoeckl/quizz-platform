package com.quiz_platform.authenticationdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String jwtToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    /**
     * Indicates whether the session is active and the user is currently logged in.
     * This field helps to keep track of active user sessions without relying solely on the existence of a session token.
     * It allows for efficient checks and cleanup of sessions, especially in cases where a session may be invalidated
     * or where multiple sessions need to be managed for a single user.
     */
    @Column(nullable = false)
    private boolean isLoggedIn;

    public Session(String jwtToken, User user, boolean isLoggedIn) {
        this.jwtToken = jwtToken;
        this.user = user;
        this.isLoggedIn = isLoggedIn;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
