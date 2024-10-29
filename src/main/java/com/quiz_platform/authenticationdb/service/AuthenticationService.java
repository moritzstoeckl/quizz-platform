package com.quiz_platform.authenticationdb.service;

import com.quiz_platform.authenticationdb.entity.Session;
import com.quiz_platform.authenticationdb.entity.User;
import com.quiz_platform.authenticationdb.response.AuthResponse;
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
     * If valid a new session is created.
     *
     * @param username
     * @param password
     * @return an Optional with AuthResponse if valid or empty if the credentials are invalid.
     */
    public Optional<AuthResponse> login(String username, String password) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            Session session = sessionService.createSession(user.get());
            AuthResponse authResponse = new AuthResponse(session.getJwtToken(), session.getExpiresAt());
            return Optional.of(authResponse);
        } else {
            return Optional.empty();  // Invalid credentials
        }
    }

    public boolean logout(String sessionToken) {
        return sessionService.deactivateToken(sessionToken);
    }
}
