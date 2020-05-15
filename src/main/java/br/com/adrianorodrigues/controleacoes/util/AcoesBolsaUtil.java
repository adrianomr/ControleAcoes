package br.com.adrianorodrigues.controleacoes.util;


import br.com.adrianorodrigues.controleacoes.model.Acao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AcoesBolsaUtil {
    public static final String BTG_FUNDO_DE_FUNDOS = "BTG Fundo de Fundos";
    static HashMap<String, Acao> acoes;

    AcoesBolsaUtil() {

    }

    public static Map<String, Acao> getAcoes() {
        if (acoes == null) {
            acoes = new HashMap<>();
            acoes.put("BCFF11", new Acao(1l, "BCFF11", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("B3SA3", new Acao(2l, "B3SA3", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("ABEV3", new Acao(3l, "ABEV3", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("ITUB4", new Acao(4l, "ITUB4", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("BIDI4", new Acao(5l, "BIDI4", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("VIVT4", new Acao(6l, "VIVT4", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("WIZS3", new Acao(7l, "WIZS3", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("XPLG11", new Acao(8l, "XPLG11", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("HGRE11", new Acao(9l, "HGRE11", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
            acoes.put("ITUB3", new Acao(10l, "ITUB3", BTG_FUNDO_DE_FUNDOS, 8, BigDecimal.valueOf(99.50)));
        }
        return acoes;
    }

    public static Acao buscaAcaoPorPapel(String papel){
        return getAcoes().get(papel);
    }
}
