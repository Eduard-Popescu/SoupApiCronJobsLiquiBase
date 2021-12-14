package com.example.demo.service.implementation;

import com.example.demo.domain.dto.input.Credentials;
import com.example.demo.domain.dto.output.Token;
import com.example.demo.security.TokenGenerator;
import com.example.demo.service.api.AuthorizationService;
import com.example.demo.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final TokenGenerator tokenGenerator;
    private final PersonService personService;

    @Autowired
    public AuthorizationServiceImpl(TokenGenerator tokenGenerator, PersonService personService) {
        this.tokenGenerator = tokenGenerator;
        this.personService = personService;
    }

    public Token login(Credentials credentials) {
        return new Token(generateToken(credentials.getEmail()));
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate();

        SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logout.logout(request, response, authentication);
        logout.setClearAuthentication(true);
    }

    String generateToken(String email) {
        UserDetails userDetails = this.getUserDetails(email);
        return tokenGenerator.generateToken(userDetails);
    }

    UserDetails getUserDetails(String username) {
        return personService.loadUserByUsername(username);
    }
}
