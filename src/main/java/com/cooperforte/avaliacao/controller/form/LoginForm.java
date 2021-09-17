package com.cooperforte.avaliacao.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
public class LoginForm {
    private String nome;
    private String senha;

    @Deprecated
    public LoginForm() {

    }

    /** */
    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.nome, this.senha);
    }
}
