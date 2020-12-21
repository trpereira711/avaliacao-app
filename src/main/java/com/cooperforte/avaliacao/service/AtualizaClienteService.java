package com.cooperforte.avaliacao.service;

import com.cooperforte.avaliacao.controller.form.AtualizaClienteForm;
import com.cooperforte.avaliacao.controller.form.EmailClienteForm;
import com.cooperforte.avaliacao.controller.form.TelefoneForm;
import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.model.EmailCliente;
import com.cooperforte.avaliacao.model.Telefone;
import com.cooperforte.avaliacao.model.dto.ClienteDto;
import com.cooperforte.avaliacao.repository.EmailClienteRepository;
import com.cooperforte.avaliacao.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class AtualizaClienteService {
    @Autowired
    private EmailClienteRepository emailClienteRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;


    /** */
    public ClienteDto atualizar(Cliente cliente, AtualizaClienteForm form) {
        form.atualiza(cliente);
        form.getEndereco().atualizar(cliente.getEndereco());

        atualizaEmails(form.getEmails(), cliente.getEmails());
        atualizaTelefones(form.getTelefones(), cliente.getTelefones());

        return new ClienteDto(cliente);
    }

    /* */
    private void atualizaEmails(Collection<EmailClienteForm> emailsForm, Collection<EmailCliente> emails) {
        emailsForm.forEach(formEmail -> {
            emails.forEach(email -> {
                if (Objects.nonNull(formEmail.getId())) {
                    if (formEmail.getId().equals(email.getId())) {
                        formEmail.atualizar(email);
                    }
                } else {
                    EmailCliente novoEmail = formEmail.converte(email.getCliente());
                    emailClienteRepository.save(novoEmail);
                }
            });
        });
    }

    /* */
    private void atualizaTelefones(Collection<TelefoneForm> telefonesForm, Collection<Telefone> telefones) {
        telefonesForm.forEach(formTelefone -> {
            telefones.forEach(telefone -> {
                if (Objects.nonNull(formTelefone.getId())) {
                    if (formTelefone.getId().equals(telefone.getId())) {
                        formTelefone.atualizar(telefone);
                    }
                } else {
                    Telefone novoTelefone = formTelefone.converte(telefone.getCliente());
                    telefoneRepository.save(novoTelefone);
                }
            });
        });
    }
}
