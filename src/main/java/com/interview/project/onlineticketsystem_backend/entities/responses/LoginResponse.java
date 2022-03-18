package com.interview.project.onlineticketsystem_backend.entities.responses;

import com.interview.project.onlineticketsystem_backend.entities.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Login Response
 */
@Getter
@Setter
@ToString(exclude = {"token"})
public class LoginResponse {

    /**
     * The user id
     */
    private long id;

    /**
     * The role
     */
    private Role role;

    /**
     * The token
     */
    private String token;

    /**
     * The First Name
     */
    private String firstName;

    /**
     * The Last Name
     */
    private String lastName;

    /**
     * The Email
     */
    private String email;

    /**
     * The username.
     */
    private String username;

}
