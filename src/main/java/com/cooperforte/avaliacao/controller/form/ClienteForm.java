package com.cooperforte.avaliacao.controller.form;

import com.cooperforte.avaliacao.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class ClienteForm {
    @NotBlank @Length(min = 3, max = 100)
    private String nome;

    @NotBlank @CPF
    private String cpf;

    @Valid @NotNull
    private EnderecoForm endereco;

    @Valid @Size(min = 1, message = "mínimo 1")
    private Collection<EmailClienteForm> emails;

    @Valid @Size(min = 1, message = "mínimo 1")
    private Collection<TelefoneForm> telefones;

    @Deprecated
    public ClienteForm() {

    }

    /** */
    public Cliente converte() {
          return new Cliente(this.nome, this.cpf, this.endereco.converte(),
                EmailClienteForm.converte(emails),TelefoneForm.converte(this.telefones));
    }

}
