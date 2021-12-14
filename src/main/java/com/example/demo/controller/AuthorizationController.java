package com.example.demo.controller;

import com.example.demo.domain.dto.input.Credentials;
import com.example.demo.domain.dto.output.Token;
import com.example.demo.service.api.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid Credentials credentials) {
        log.info("credentials = {}",credentials);
        return ResponseEntity.ok(authorizationService.login(credentials));
    }
}
