package com.interview.project.onlineticketsystem_backend.entities.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Login Request
 */
@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    /**
     * The username
     */
    @NotBlank
    @Size(min = 1, max = 255)
    private String username;

    /**
     * The password
     */
    @NotBlank
    @Size(min = 1, max = 255)
    private String password;
}
