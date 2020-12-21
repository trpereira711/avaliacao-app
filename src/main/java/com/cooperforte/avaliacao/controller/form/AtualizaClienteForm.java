package com.cooperforte.avaliacao.controller.form;

import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class AtualizaClienteForm {
    @NotBlank
    @Length(min = 3, max = 100)
    private String nome;

    @Valid @NotNull
    private EnderecoForm endereco;

    @Valid @Size(min = 1, message = "mínimo 1")
    private Collection<EmailClienteForm> emails;

    @Valid @Size(min = 1, message = "mínimo 1")
    private Collection<TelefoneForm> telefones;

    @Deprecated
    public AtualizaClienteForm() {

    }

    /** */
    public void atualiza(Cliente cliente) {
        cliente.setNome(this.nome);
    }
}
