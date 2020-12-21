package com.cooperforte.avaliacao.config.validation.security;

import com.cooperforte.avaliacao.model.Usuario;
import com.cooperforte.avaliacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Usuario> usuario = repository.findByNome(username);

        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dádos inválidos");
    }
}
