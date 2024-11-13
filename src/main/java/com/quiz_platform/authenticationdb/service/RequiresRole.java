package com.quiz_platform.authenticationdb.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for specifying a required role for a method.
 * Used to enforce role-based access control on endpoints.
 * <p>
 * Methods annotated with {@code @RequiresRole} will be intercepted by
 * an aspect (e.g., {@link RoleAspect}) to verify if the calling user
 * has the necessary role as specified in the annotation.
 * If the user does not have the required role, an exception will be thrown.
 * </p>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * @RequiresRole("ADMIN")
 * public void adminOnlyMethod() {
 *     // method implementation
 * }
 * }</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    String value();
}
