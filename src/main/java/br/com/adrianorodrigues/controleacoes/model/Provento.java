package br.com.adrianorodrigues.controleacoes.model;

import java.math.BigDecimal;
import java.util.Date;

public class Provento {
    private int id;
    private Acao acao;
    private Date dataPagamento;
    private BigDecimal valor;

    public Provento(int id, Acao acao, Date dataPagamento, BigDecimal valor) {
        this.id = id;
        this.acao = acao;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
