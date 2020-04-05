package br.com.adrianorodrigues.ControleAcoes.model;

import javax.persistence.*;

@Entity
@Table(name = "cotacao")
public class Cotacao {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "numeric(20,4)")
    private Double valorFechamento;

    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(Double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }
}
