package com.cooperforte.avaliacao.model.dto;

import com.cooperforte.avaliacao.model.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TelefoneDto {
    private Long id;
    private String ddd;
    private String numero;
    private String tipo;

    @Deprecated
    public TelefoneDto() {

    }

    public TelefoneDto(Telefone telefone) {
        this.id = telefone.getId();
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
        this.tipo = telefone.getTipoTelefone().name();
    }

    public static List<TelefoneDto> converte(Collection<Telefone> telefones) {
        return telefones.stream().map(TelefoneDto::new).collect(Collectors.toList());
    }


}
