package com.interview.project.onlineticketsystem_backend.securities;

import com.interview.project.onlineticketsystem_backend.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This class provides utility methods related to security.
 */
public abstract class SecurityUtil {

    /**
     * Private constructor
     */
    private SecurityUtil() {

    }

    /**
     * Get current Logged in user.
     *
     * @return the current Logged in user.
     */
    public static User getCurrentUser() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return null;
    }
}
