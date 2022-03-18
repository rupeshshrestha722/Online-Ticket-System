package com.interview.project.onlineticketsystem_backend.services;

import java.util.Optional;

import com.interview.project.onlineticketsystem_backend.entities.User;
import com.interview.project.onlineticketsystem_backend.entities.requests.LoginRequest;
import com.interview.project.onlineticketsystem_backend.entities.responses.LoginResponse;
import com.interview.project.onlineticketsystem_backend.repositories.UserRepository;
import com.interview.project.onlineticketsystem_backend.securities.jwt.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The service provides security related operations.
 */
@Service
public class SecurityService {

    /**
     * The user repository.
     */
    @Autowired
    private UserRepository repository;

    /**
     * The token utils.
     */
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * The password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Login a user.
     *
     * @param request the login request
     * @return the login response
     */
    @Transactional(noRollbackFor = BadCredentialsException.class)
    public LoginResponse login(LoginRequest request) {
        // Get the user by username
        User probe = new User();
        probe.setUsername(request.getUsername());
        Optional<User> optionalUser = repository.findOne(Example.of(probe));

        // Check if user does not exist
        if (!optionalUser.isPresent()) {
            throw new BadCredentialsException("The username or password is incorrect");
        }

        User user = optionalUser.get();

        // Check if password does not match
        if (!passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            repository.saveAndFlush(user);
            throw new BadCredentialsException(
                    "The username or password is incorrect");
        }

        // Build the response
            LoginResponse response = new LoginResponse();

            response.setToken(tokenUtils.createJwtToken(user));
            response.setId(user.getId());
            response.setRole(user.getRole());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUsername(user.getUsername());
            return response;
    }
}

