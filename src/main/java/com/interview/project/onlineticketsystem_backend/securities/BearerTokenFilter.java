package com.interview.project.onlineticketsystem_backend.securities;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interview.project.onlineticketsystem_backend.entities.User;
import com.interview.project.onlineticketsystem_backend.securities.jwt.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The filter to process bearer authentication token.
 */
public class BearerTokenFilter extends OncePerRequestFilter {

    /**
     * The bearer prefix.
     */
    private static final String BEARER_PREFIX = "Bearer ";

    /**
     * The token utils.
     */
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * Process the request.
     *
     * @param request
     *          the HTTP servlet request
     * @param response
     *          the HTTP servlet response
     * @param chain
     *          the filter chain
     * @throws IOException
     *           if an I/O error occurs during the processing of the request
     * @throws ServletException
     *           if the processing fails for any other reason
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken = bearerToken.substring(BEARER_PREFIX.length());
        }

        if (!StringUtils.isEmpty(bearerToken)) {
            // Decode the token
            User user = tokenUtils.decodeJwtToken(bearerToken);

            // Set the user info to security context
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                    Arrays.asList(authority));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
