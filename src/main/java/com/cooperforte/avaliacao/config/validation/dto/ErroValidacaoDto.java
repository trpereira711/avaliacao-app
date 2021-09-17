package com.cooperforte.avaliacao.config.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroValidacaoDto {
    private String campo;
    private String mensagem;

    @Deprecated
    public ErroValidacaoDto() {

    }
}
