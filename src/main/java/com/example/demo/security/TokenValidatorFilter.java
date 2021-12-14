package com.example.demo.security;

import com.example.demo.exception.ExpiredTokenException;
import com.example.demo.service.api.PersonService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenValidatorFilter extends OncePerRequestFilter {


    private final PersonService jwtUserDetailsService;
    private final TokenGenerator tokenGenerator;

    public TokenValidatorFilter(PersonService jwtUserDetailsService, TokenGenerator tokenGenerator) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (isMissing(token)) {
            forbidden(response);
            return;
        }

        String username = getUsernameFromToken(token);
        if (userIsNotAuthenticated(username)) {
            forbidden(response);
            return;
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        if (invalidToken(token, userDetails)) {
            forbidden(response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(request, userDetails);
        setAuthenticationTokenInSecurityContext(authenticationToken);
        filterChain.doFilter(request, response);
    }
    private void forbidden(HttpServletResponse response) throws IOException {
        response.sendError(403, "Unauthorised");
    }

    private void setAuthenticationTokenInSecurityContext(UsernamePasswordAuthenticationToken authToken) {
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    private Boolean invalidToken(String token, UserDetails userDetails) {
        return !tokenGenerator.validateToken(token, userDetails);
    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isMissing(String in) {
        return in == null || in.isEmpty();
    }

    private String getUsernameFromToken(String jwtToken) {
        try {
            return tokenGenerator.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Unable to get Token.");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new ExpiredTokenException("Token has expired.");
        }
    }

    private boolean userIsNotAuthenticated(String username) {
        return isMissing(username) || SecurityContextHolder.getContext().getAuthentication() != null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getPathInfo();
        return path.startsWith("/login");
    }
}
