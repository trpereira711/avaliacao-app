package com.cooperforte.avaliacao.config.validation.security;

import com.cooperforte.avaliacao.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${cliente.jwt.expiration}")
    private String expiration;

    @Value("${cliente.jwt.secret}")
    private String secret;

    /** */
    public String gerar(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration)); //sim eu sei que está deprecate, por motivos práticos...

        return Jwts.builder()
                .setIssuer("API AVALIAÇÃO DEV SÊNIOR")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();

    }

    /** */
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** */
    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
