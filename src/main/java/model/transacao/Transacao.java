package model.transacao;

import model.Acao;

import java.math.BigDecimal;

public class Transacao {
    private int id;
    private Acao acao;
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;

    public Transacao(int id, Acao acao, TipoTransacao tipoTransacao, BigDecimal valor) {
        this.id = id;
        this.acao = acao;
        this.tipoTransacao = tipoTransacao;
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

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
