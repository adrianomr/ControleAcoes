package model.transacao;

import java.math.BigDecimal;

public enum TipoTransacao {

    COMPRA("Compra", new BigDecimal(1)), VENDA("Venda", new BigDecimal(2));

    private String nome;
    private BigDecimal valor;

    TipoTransacao(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
