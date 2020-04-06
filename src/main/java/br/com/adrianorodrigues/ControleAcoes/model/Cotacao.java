package br.com.adrianorodrigues.ControleAcoes.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotacao")
public class Cotacao {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date data;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "id=" + id +
                ", data=" + data +
                ", valorFechamento=" + valorFechamento +
                ", acao=" + acao +
                '}';
    }
}
