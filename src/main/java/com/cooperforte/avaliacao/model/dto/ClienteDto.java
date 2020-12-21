package com.cooperforte.avaliacao.model.dto;

import com.cooperforte.avaliacao.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private EnderecoDto endereco;
    private Collection<TelefoneDto> telefones;
    private Collection<EmailClienteDto> emails;

    @Deprecated
    public ClienteDto() {

    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.endereco = new EnderecoDto(cliente.getEndereco());
        this.telefones = TelefoneDto.converte(cliente.getTelefones());
        this.emails = EmailClienteDto.converte(cliente.getEmails());
    }

    /** */
    public static List<ClienteDto> converte(Collection<Cliente> clientes) {
         return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }
}
