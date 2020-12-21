package com.cooperforte.avaliacao.controller.form;

import com.cooperforte.avaliacao.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class EnderecoForm {
    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    private String complemento;

    @Deprecated
    public EnderecoForm() {
    }

    /** */
    public Endereco converte() {
        return new Endereco(cep, logradouro, bairro, cidade, uf, complemento);
    }

    /** */
    public void atualizar(Endereco endereco) {
        endereco.setCep(this.cep);
        endereco.setLogradouro(this.logradouro);
        endereco.setBairro(this.bairro);
        endereco.setCidade(this.cidade);
        endereco.setUf(this.uf);
        endereco.setComplemento(this.complemento);
    }
}
