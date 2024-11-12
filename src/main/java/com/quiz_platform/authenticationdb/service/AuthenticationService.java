package com.quiz_platform.authenticationdb.service;

import com.quiz_platform.authenticationdb.entity.Session;
import com.quiz_platform.authenticationdb.entity.User;
import com.quiz_platform.controller.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean verifyToken(String token) {
        return sessionService.verifyToken(token);
    }

    /**
     * Authenticates a user by validating the provided username and password.
     * If valid, a new session is created and returned in the response.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return an AuthResponse with either a success message and token or a failure message.
     */
    public AuthResponse login(String username, String password) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isEmpty()) {
            return new AuthResponse(null, "User not found", true);
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return new AuthResponse(null, "Incorrect password", true);
        }

        Session session = sessionService.createSession(user.get());
        return new AuthResponse(session.getJwtToken(), "Successfully logged in", false);
    }


    public boolean logout(String sessionToken) {
        return sessionService.deactivateToken(sessionToken);
    }
}
