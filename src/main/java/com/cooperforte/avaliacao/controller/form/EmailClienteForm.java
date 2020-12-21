package com.cooperforte.avaliacao.controller.form;

import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.model.EmailCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EmailClienteForm {

    private Long id;

    @Email @NotBlank
    private String email;

    @Deprecated
    public EmailClienteForm() {

    }

    /** */
    public EmailCliente converte() {
        return new EmailCliente(this.email);
    }

    /** */
    public EmailCliente converte(Cliente cliente) {
        return new EmailCliente(this.email, cliente);
    }

    /** */
    public static Set<EmailCliente> converte(Collection<EmailClienteForm> emails)  {
        return emails.stream()
                .map(form -> form.converte())
                .collect(Collectors.toSet());
    }

    /** */
    public void atualizar(EmailCliente email) {
        email.setEmail(this.email);
    }

    /** */
    public void atualizar(Collection<EmailCliente> emails) {
        emails.forEach(e -> this.atualizar(e));
    }
}
