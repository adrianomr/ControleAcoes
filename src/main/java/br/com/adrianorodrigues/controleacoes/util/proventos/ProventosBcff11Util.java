package br.com.adrianorodrigues.controleacoes.util.proventos;

import br.com.adrianorodrigues.controleacoes.model.Provento;
import br.com.adrianorodrigues.controleacoes.util.AcoesBolsaUtil;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProventosBcff11Util {
    public static final String BCFF_11 = "BCFF11";

    private ProventosBcff11Util() {

    }

    private static ArrayList<Provento> proventos;

    public static List<Provento> getProventos(Map<Long, List<Provento>> proventosPorAcao) throws ParseException {
        if (proventos == null) {
            proventos = new ArrayList<>();
            proventos.add(new Provento(1l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/01/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(2l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/02/2019"),
                    BigDecimal.valueOf(0.43)));
            proventos.add(new Provento(3l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/03/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(4l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),

                    DateFromStringUtil.getDate("14/04/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(5l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/05/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(6l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/06/2019"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(7l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/07/2019"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(8l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/08/2019"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(9l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/09/2019"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(10l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/10/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(11l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/11/2019"),
                    BigDecimal.valueOf(0.53)));
            proventos.add(new Provento(12l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/12/2019"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(13l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/01/2020"),
                    BigDecimal.valueOf(0.57)));
            proventos.add(new Provento(14l, AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11),
                    DateFromStringUtil.getDate("14/02/2020"),
                    BigDecimal.valueOf(0.57)));
        }
        proventosPorAcao.put(AcoesBolsaUtil.buscaAcaoPorPapel(BCFF_11).getId(), proventos);
        return proventos;
    }
}
