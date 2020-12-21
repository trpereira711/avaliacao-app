package com.cooperforte.avaliacao.repository;

import com.cooperforte.avaliacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);
}
