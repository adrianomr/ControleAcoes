package br.com.adrianorodrigues.ControleAcoes.bo;

import br.com.adrianorodrigues.ControleAcoes.model.Provento;
import br.com.adrianorodrigues.ControleAcoes.util.proventos.ProventosUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class ProventosPeriodoBo {
    public static ArrayList<Provento> getProventosPeriodo(final Date dataInicial, final Date dataFinal, Long idPapel) throws ParseException {
        ArrayList<Provento> proventos = ProventosUtil.getProventos().get(idPapel);
        proventos =
                (ArrayList<Provento>) proventos
                        .stream()
                        .filter(provento -> provento.getDataPagamento().compareTo(dataInicial) >= 0 && provento.getDataPagamento().compareTo(dataFinal) <= 0)
                        .collect(Collectors.toList());
        return proventos;
    }
}
