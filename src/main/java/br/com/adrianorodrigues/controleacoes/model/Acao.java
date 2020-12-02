package br.com.adrianorodrigues.controleacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "acao")
public class Acao {
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "varchar(100)")
    private String papel;
    @Column(columnDefinition = "varchar(100)")
    private String codigoBdi;
    @Column
    private Integer tipoMercado;
    @Column(columnDefinition = "varchar(100)")
    private String nomeEmpresa;
    @Column(columnDefinition = "numeric(20)")
    private int quantidade;
    @Column(columnDefinition = "numeric(20,4)")
    private BigDecimal valor;

    public Acao(Long id, String papel, String nomeEmpresa, int quantidade, BigDecimal valor) {
        this.id = id;
        this.papel = papel;
        this.nomeEmpresa = nomeEmpresa;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Acao(Long id) {
        this.id = id;
    }

    public Acao() {

    }
}
