package com.quiz_platform.authenticationdb.repository;

import com.quiz_platform.authenticationdb.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByJwtToken(String jwtToken);
}
