package br.com.adrianorodrigues.controleacoes.bo;

import br.com.adrianorodrigues.controleacoes.model.Provento;
import br.com.adrianorodrigues.controleacoes.util.AcoesBolsaUtil;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProventosPeriodoBoTest {

    @Test
    public void getProventosPeriodo() throws ParseException {
        List<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromStringUtil.getDate("01/01/2019"),
                DateFromStringUtil.getDate("01/01/2021"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(14, proventos.size());
    }

    @Test
    public void getProventosPeriodoNaoDeveRetornarProventos() throws ParseException {
        List<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromStringUtil.getDate("01/01/2019"),
                DateFromStringUtil.getDate("01/01/2019"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(0, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataIgualInicial() throws ParseException {
        List<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromStringUtil.getDate("14/10/2019"),
                DateFromStringUtil.getDate("01/01/2021"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(5, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataIgualFinal() throws ParseException {
        List<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromStringUtil.getDate("01/10/2019"),
                DateFromStringUtil.getDate("14/02/2020"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(5, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataFinalIgualInicial() throws ParseException {
        List<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromStringUtil.getDate("15/10/2019"),
                DateFromStringUtil.getDate("15/10/2019"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(0, proventos.size());
    }

}