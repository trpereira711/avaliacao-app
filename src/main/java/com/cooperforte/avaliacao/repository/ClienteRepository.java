package com.cooperforte.avaliacao.repository;

import com.cooperforte.avaliacao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    
}
