package br.com.adrianorodrigues.ControleAcoes.util;


import br.com.adrianorodrigues.ControleAcoes.model.Acao;

import java.math.BigDecimal;
import java.util.HashMap;

public class AcoesBolsaUtil {
    static HashMap<String, Acao> acoes;

    public static HashMap<String, Acao> getAcoes() {
        if(acoes == null){
            acoes = new HashMap<String, Acao>();
            acoes.put("BCFF11", new Acao(1l, "BCFF11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("B3SA3", new Acao(2l, "B3SA3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ABEV3", new Acao(3l, "ABEV3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ITUB4", new Acao(4l, "ITUB4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("BIDI4", new Acao(5l, "BIDI4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("VIVT4", new Acao(6l, "VIVT4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("WIZS3", new Acao(7l, "WIZS3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("XPLG11", new Acao(8l, "XPLG11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("HGRE11", new Acao(9l, "HGRE11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ITUB3", new Acao(10l, "ITUB3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
        }
        return acoes;
    }

    public static Acao buscaAcaoPorPapel(String papel){
        return getAcoes().get(papel);
    }
}
