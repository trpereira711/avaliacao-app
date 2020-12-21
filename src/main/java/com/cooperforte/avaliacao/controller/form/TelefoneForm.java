package com.cooperforte.avaliacao.controller.form;

import com.cooperforte.avaliacao.model.Cliente;
import com.cooperforte.avaliacao.model.Telefone;
import com.cooperforte.avaliacao.model.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TelefoneForm {

    private Long id;

    @NotBlank
    private String numero;

    @NotBlank
    private String ddd;

    @NotBlank
    private String tipo;


    @Deprecated
    public TelefoneForm() {

    }

    /** */
    public Telefone converte() {
        return new Telefone(numero, ddd, tipo);
    }

    /** */
    public Telefone converte(Cliente cliente) {
        return new Telefone(numero, ddd, tipo, cliente);
    }

    /** */
    public static Set<Telefone> converte(Collection<TelefoneForm> telefones) {
        return telefones.stream()
                .map(form -> form.converte())
                .collect(Collectors.toSet());
    }

    /** */
    public void atualizar(Telefone telefone) {
        telefone.setDdd(this.ddd);
        telefone.setNumero(this.numero);
        telefone.setTipoTelefone(TipoTelefone.valueOf(this.tipo));
    }

    /** */
    public void atualizar(Collection<Telefone> telefones) {
        telefones.forEach(t -> this.atualizar(t));
    }

}
