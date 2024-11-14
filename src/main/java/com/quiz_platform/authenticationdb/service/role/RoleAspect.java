package com.quiz_platform.authenticationdb.service.role;

import com.quiz_platform.authenticationdb.service.RoleChecker;
import com.quiz_platform.authenticationdb.service.exception.UnauthorizedAccessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for handling role-based authorization. checks if the user has the required role
 * based on their JWT token.
 */
@Aspect
@Component
public class RoleAspect {

    @Autowired
    private RoleChecker roleChecker;

    /**
     * Checks if the user has the required role.
     *
     * @param joinPoint    The join point providing access to method arguments.
     * @param requiresRole The {@link RequiresRole} annotation containing the required role.
     * @throws UnauthorizedAccessException if the token is missing or the user does not have the required role.
     */
    @Before("@annotation(requiresRole)")
    public void checkRole(JoinPoint joinPoint, RequiresRole requiresRole) {
        String requiredRole = requiresRole.value();

        String jwtToken = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof String && ((String) arg).startsWith("ey"))
                .map(arg -> (String) arg)
                .findFirst()
                .orElseThrow(() -> new UnauthorizedAccessException("No token provided"));

        if (!roleChecker.hasRole(jwtToken, requiredRole)) {
            throw new UnauthorizedAccessException("Unauthorized access - required role: " + requiredRole);
        }
    }
}
