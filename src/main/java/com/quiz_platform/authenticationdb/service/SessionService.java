package com.quiz_platform.authenticationdb.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quiz_platform.authenticationdb.entity.Session;
import com.quiz_platform.authenticationdb.entity.User;
import com.quiz_platform.authenticationdb.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * New sessions are created when user logs in. But they are never deleted.
     * That's why cleanup is needed.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredSessions() {
        List<Session> allSessions = sessionRepository.findAll();

        for (Session session : allSessions) {
            Date expiresAt = getExpiryDate(session.getJwtToken());
            if (expiresAt.before(new Date())) {
                sessionRepository.delete(session);
            }
        }
    }

    /**
     * Verifies the validity of a JWT token by checking the session and token status.
     *
     * @param jwtToken the JWT token to verify.
     * @return true if the session is active, unexpired, and the JWT token is valid; false otherwise.
     */
    public boolean verifyToken(String jwtToken) {
        Optional<Session> sessionOptional = sessionRepository.findByJwtToken(jwtToken);

        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            if (session.isActive() && getExpiryDate(session.getJwtToken()).before(new Date())) {
                try {
                    jwtUtil.verifyToken(jwtToken);
                    return true;
                } catch (JWTVerificationException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public Session createSession(User user) {
        String jwtToken = jwtUtil.generateToken(user.getUsername());

        DecodedJWT decodedJWT = jwtUtil.verifyToken(jwtToken);
        LocalDateTime expiresAt = LocalDateTime.ofInstant(decodedJWT.getExpiresAt().toInstant(), ZoneId.systemDefault());
        Session session = new Session(jwtToken, user, true);

        return sessionRepository.save(session);
    }

    /**
     * @param jwtToken the JWT token of the session to deactivate.
     * @return true if the session was successfully deactivated; false if the session was not found or was already inactive.
     */
    public boolean deactivateToken(String jwtToken) {
        Optional<Session> sessionOptional = sessionRepository.findByJwtToken(jwtToken);
        if (sessionOptional.isPresent() && sessionOptional.get().isActive()) {
            Session session = sessionOptional.get();
            session.setActive(false);
            sessionRepository.save(session);
            return true;
        }
        return false;
    }

    public Date getExpiryDate(String jwtToken) {
        return jwtUtil.decodeJWT(jwtToken).getExpiresAt();
    }
}
