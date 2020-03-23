package br.com.adrianorodrigues.ControleAcoes.bo;

import br.com.adrianorodrigues.ControleAcoes.model.Provento;
import br.com.adrianorodrigues.ControleAcoes.util.AcoesBolsaUtil;
import br.com.adrianorodrigues.ControleAcoes.util.DateFromString;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProventosPeriodoBoTest {

    @Test
    public void getProventosPeriodo() throws ParseException {
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromString.getDate("01/01/2019"),
                DateFromString.getDate("01/01/2021"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(14, proventos.size());
    }

    @Test
    public void getProventosPeriodoNaoDeveRetornarProventos() throws ParseException {
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromString.getDate("01/01/2019"),
                DateFromString.getDate("01/01/2019"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(0, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataIgualInicial() throws ParseException {
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromString.getDate("14/10/2019"),
                DateFromString.getDate("01/01/2021"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(5, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataIgualFinal() throws ParseException {
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromString.getDate("01/10/2019"),
                DateFromString.getDate("14/02/2020"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(5, proventos.size());
    }

    @Test
    public void getProventosPeriodoDataFinalIgualInicial() throws ParseException {
        ArrayList<Provento> proventos = ProventosPeriodoBo.getProventosPeriodo(DateFromString.getDate("15/10/2019"),
                DateFromString.getDate("15/10/2019"), AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11").getId());
        assertEquals(0, proventos.size());
    }

}