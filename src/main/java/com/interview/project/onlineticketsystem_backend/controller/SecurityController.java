package com.interview.project.onlineticketsystem_backend.controller;

import com.interview.project.onlineticketsystem_backend.entities.User;
import com.interview.project.onlineticketsystem_backend.entities.requests.LoginRequest;
import com.interview.project.onlineticketsystem_backend.entities.responses.LoginResponse;
import com.interview.project.onlineticketsystem_backend.securities.SecurityUtil;
import com.interview.project.onlineticketsystem_backend.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The controller defines security related endpoints.
 */
@RestController
public class SecurityController {

    /**
     * The security service.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Authenticate the login request.
     *
     * @param request the login request
     * @return the login response
     */
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return securityService.login(request);
    }

}
