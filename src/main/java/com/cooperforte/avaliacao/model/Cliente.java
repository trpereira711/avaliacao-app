package com.cooperforte.avaliacao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Audited
@AuditTable(value="cliente_audit")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmailCliente> emails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Telefone> telefones;

    public Cliente(String nome, String cpf, Endereco endereco, Collection<EmailCliente> emails, Collection<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        emails.forEach(this::addEmail);
        telefones.forEach(this::addTelefone);
    }

    public Cliente(String nome, Endereco endereco, Collection<EmailCliente> emails, Collection<Telefone> telefones) {
        this.nome = nome;
        this.endereco = endereco;
        emails.forEach(this::addEmail);
        telefones.forEach(this::addTelefone);
    }

    /** */
    public void addEmail(EmailCliente email) {
        if (Objects.isNull(emails)) {
            emails = new HashSet<>();
        }
        email.setCliente(this);
        emails.add(email);
    }

    /** */
    public void addTelefone(Telefone telefone) {
        if (Objects.isNull(telefones)) {
            telefones = new HashSet<>();
        }
        telefone.setCliente(this);
        telefones.add(telefone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf);
    }

}
