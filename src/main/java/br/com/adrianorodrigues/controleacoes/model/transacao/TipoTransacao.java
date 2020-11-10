package br.com.adrianorodrigues.controleacoes.model.transacao;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@Getter
public enum TipoTransacao {

    COMPRA("Compra", new BigDecimal(1)), VENDA("Venda", new BigDecimal(2));

    private final String nome;
    private final BigDecimal valor;

    TipoTransacao(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
