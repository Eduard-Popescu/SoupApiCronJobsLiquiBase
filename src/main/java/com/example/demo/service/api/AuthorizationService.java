package com.example.demo.service.api;

import com.example.demo.domain.dto.input.Credentials;
import com.example.demo.domain.dto.output.Token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthorizationService {

    Token login(Credentials credentials);
    void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
