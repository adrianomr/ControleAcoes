package br.com.adrianorodrigues.ControleAcoes.bo;

import br.com.adrianorodrigues.ControleAcoes.util.AcoesBolsaUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class DividendYeldBoTest {

    @Test
    public void getDividendYeld() throws ParseException {
        System.out.println(DividendYeldBo.getDividendYeld(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), 24));
    }
}