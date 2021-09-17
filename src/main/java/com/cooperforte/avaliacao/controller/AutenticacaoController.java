package com.cooperforte.avaliacao.controller;

import com.cooperforte.avaliacao.config.validation.security.TokenService;
import com.cooperforte.avaliacao.controller.form.LoginForm;
import com.cooperforte.avaliacao.model.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        try {
            UsernamePasswordAuthenticationToken login = form.converter();
            Authentication authentication = authenticationManager.authenticate(login);
            String token  = tokenService.gerar(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Barear"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
