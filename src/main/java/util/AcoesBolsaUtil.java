package util;

import model.Acao;

import java.math.BigDecimal;
import java.util.HashMap;

public class AcoesBolsaUtil {
    static HashMap<String, Acao> acoes;

    public static HashMap<String, Acao> getAcoes() {
        if(acoes == null){
            acoes = new HashMap<String, Acao>();
            acoes.put("BCFF11",new Acao(1, "BCFF11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("B3SA3", new Acao(2, "B3SA3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ABEV3", new Acao(3, "ABEV3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ITUB4", new Acao(4, "ITUB4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("BIDI4", new Acao(5, "BIDI4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("VIVT4", new Acao(6, "VIVT4", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("WIZS3", new Acao(7, "WIZS3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("XPLG11", new Acao(8, "XPLG11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("HGRE11", new Acao(9, "HGRE11", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
            acoes.put("ITUB3", new Acao(10, "ITUB3", "BTG Fundo de Fundos", 8, new BigDecimal(99.50)));
        }
        return acoes;
    }

    public static Acao buscaAcaoPorPapel(String papel){
        return getAcoes().get(papel);
    }
}
