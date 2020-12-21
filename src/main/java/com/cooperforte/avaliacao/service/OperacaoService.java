package com.cooperforte.avaliacao.service;

import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.model.Operacao;
import com.cooperforte.avaliacao.model.Usuario;
import com.cooperforte.avaliacao.model.enums.TipoOperacao;
import com.cooperforte.avaliacao.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository operacaoRepository;

    /** */
    public Operacao salvarOperacao(Usuario usuario, TipoOperacao tipo, Cliente cliente) {
        return operacaoRepository.save(new Operacao(usuario.getUsername(), tipo, cliente));
    }
}
