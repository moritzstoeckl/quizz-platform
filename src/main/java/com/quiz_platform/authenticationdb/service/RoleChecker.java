package com.quiz_platform.authenticationdb.service;

import com.quiz_platform.authenticationdb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleChecker {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    /**
     * Checks if the user associated with the provided JWT token has the specified role.
     *
     * @param token        The JWT token representing the user's session.
     * @param requiredRole The role required for accessing a specific resource.
     * @return {@code true} if the user has the required role; {@code false} otherwise.
     */
    public boolean hasRole(String token, String requiredRole) {
        String username = jwtUtil.decodeJWT(token).getSubject();
        Optional<User> userOptional = userService.findByUsername(username);

        return userOptional.map(user -> user.getUserRole().name().equalsIgnoreCase(requiredRole)).orElse(false);
    }
}
