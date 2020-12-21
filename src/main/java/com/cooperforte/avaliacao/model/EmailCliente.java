package com.cooperforte.avaliacao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Audited
@AuditTable(value="email_audit")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class EmailCliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    private Cliente cliente;

    public EmailCliente(String email) {
        this.email = email;
    }

    public EmailCliente(String email, Cliente cliente) {
        this.email = email;
        cliente.addEmail(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailCliente that = (EmailCliente) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
