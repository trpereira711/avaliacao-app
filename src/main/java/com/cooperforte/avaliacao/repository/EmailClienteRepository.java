package com.cooperforte.avaliacao.repository;

import com.cooperforte.avaliacao.model.EmailCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailClienteRepository extends JpaRepository<EmailCliente, Long> {
}
