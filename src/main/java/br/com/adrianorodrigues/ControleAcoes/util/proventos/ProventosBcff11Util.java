package br.com.adrianorodrigues.ControleAcoes.util.proventos;
import br.com.adrianorodrigues.ControleAcoes.model.Provento;
import br.com.adrianorodrigues.ControleAcoes.util.AcoesBolsaUtil;
import br.com.adrianorodrigues.ControleAcoes.util.DateFromString;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProventosBcff11Util {
    private static ArrayList<Provento> proventos;

    public static ArrayList<Provento> getProventos(HashMap proventosPorAcao) throws ParseException {
        if(proventos == null){
            proventos = new ArrayList<Provento>();
            proventos.add(new Provento(1, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/01" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(2, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/02" +
                    "/2019"),
                    new BigDecimal(0.43)));
            proventos.add(new Provento(3, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/03" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(4, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/04" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(5, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/05" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(6, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/06" +
                    "/2019"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(7, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/07" +
                    "/2019"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(8, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/08" +
                    "/2019"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(9, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/09" +
                    "/2019"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(10, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/10" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(11, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/11" +
                    "/2019"),
                    new BigDecimal(0.53)));
            proventos.add(new Provento(12, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/12" +
                    "/2019"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(13, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/01" +
                    "/2020"),
                    new BigDecimal(0.57)));
            proventos.add(new Provento(14, AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), DateFromString.getDate("14/02" +
                    "/2020"),
                    new BigDecimal(0.57)));
        }
        proventosPorAcao.put(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId(), proventos);
        return proventos;
    }
}
