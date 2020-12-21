package com.cooperforte.avaliacao.model.dto;

import com.cooperforte.avaliacao.model.EmailCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EmailClienteDto {
    private Long id;
    private String email;

    @Deprecated
    public EmailClienteDto() {

    }

    /** */
    public EmailClienteDto(EmailCliente email) {
        this.id = email.getId();
        this.email = email.getEmail();
    }

    /** */
    public static List<EmailClienteDto> converte(Collection<EmailCliente> emails) {
        return emails.stream().map(EmailClienteDto::new).collect(Collectors.toList());
    }

}
