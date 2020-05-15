package br.com.adrianorodrigues.controleacoes.model.transacao;

import java.math.BigDecimal;

public enum TipoTransacao {

    COMPRA("Compra", new BigDecimal(1)), VENDA("Venda", new BigDecimal(2));

    private final String nome;
    private final BigDecimal valor;

    TipoTransacao(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
