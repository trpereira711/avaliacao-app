package com.cooperforte.avaliacao.model;

import com.cooperforte.avaliacao.model.enums.TipoOperacao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Operacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeOperador;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime data = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    @OneToOne
    private Cliente cliente;

    /** */
    public Operacao(String nomeOperador, TipoOperacao tipo, Cliente cliente) {
        this.nomeOperador = nomeOperador;
        this.tipo = tipo;
        this.cliente = cliente;
    }
}
