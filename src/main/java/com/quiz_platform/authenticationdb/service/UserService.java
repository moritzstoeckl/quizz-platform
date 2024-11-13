package com.quiz_platform.authenticationdb.service;

import com.quiz_platform.authenticationdb.entity.User;
import com.quiz_platform.authenticationdb.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PersistenceContext(unitName = "authDbPU")
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Encrypts the password before storing the new user in the database.
     *
     * @param username the username of the new user (must not be null)
     * @param password the raw password of the new user (must not be null)
     * @param role     the role of the new user; if null, a default role will be assigned
     * @throws IllegalArgumentException if any required parameter is null or if the username already exists.
     */
    public void createUser(String username, String password, User.Role role) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }
        if (role.equals(User.Role.ADMIN)) {
            throw new IllegalArgumentException("You cannot create a user with an admin role.");
        }
        if (findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User with this username already exists.");
        }

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword, false, role);
        userRepository.save(user);
    }

}
