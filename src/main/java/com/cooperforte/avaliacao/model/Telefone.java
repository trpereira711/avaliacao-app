package com.cooperforte.avaliacao.model;

import com.cooperforte.avaliacao.model.enums.TipoTelefone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Audited
@AuditTable(value="telefone_audit")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Telefone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String ddd;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;

    @ManyToOne
    private Cliente cliente;

    public Telefone(String numero, String ddd, String tipo) {
        this.numero = numero;
        this.ddd = ddd;
        this.tipoTelefone = TipoTelefone.valueOf(tipo);
    }

    public Telefone(String numero, String ddd, String tipo, Cliente cliente) {
        this.numero = numero;
        this.ddd = ddd;
        this.tipoTelefone = TipoTelefone.valueOf(tipo);
        cliente.addTelefone(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(numero, telefone.numero) && Objects.equals(ddd, telefone.ddd) && tipoTelefone == telefone.tipoTelefone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, ddd, tipoTelefone);
    }
}
