package br.com.adrianorodrigues.controleacoes.bo;

import br.com.adrianorodrigues.controleacoes.model.Provento;
import br.com.adrianorodrigues.controleacoes.util.proventos.ProventosUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProventosPeriodoBo {
    public static List<Provento> getProventosPeriodo(final Date dataInicial, final Date dataFinal, Long idPapel) throws ParseException {
        List<Provento> proventos = ProventosUtil.getProventos().get(idPapel);
        proventos =
                proventos
                        .stream()
                        .filter(provento -> provento.getDataPagamento().compareTo(dataInicial) >= 0 && provento.getDataPagamento().compareTo(dataFinal) <= 0)
                        .collect(Collectors.toList());
        return proventos;
    }
}
