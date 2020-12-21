package com.cooperforte.avaliacao.controller;

import com.cooperforte.avaliacao.model.Perfil;
import com.cooperforte.avaliacao.model.Usuario;
import com.cooperforte.avaliacao.model.enums.TipoPerfil;
import com.cooperforte.avaliacao.repository.EmailClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailClienteRepository emailRepository;

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfis().contains(new Perfil(TipoPerfil.ADMIN))) {
            return emailRepository.findById(id).map(e -> {
                emailRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
