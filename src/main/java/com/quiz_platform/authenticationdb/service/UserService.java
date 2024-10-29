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
     * @param username
     * @param password
     * @return returns the saved User.
     */
    public User createUser(String username, String password) {
        if (findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User with this username already exists.");
        }
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword, false);
        return userRepository.save(user);
    }
}
