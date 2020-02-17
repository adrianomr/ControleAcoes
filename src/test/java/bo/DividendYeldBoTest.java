package bo;

import org.junit.Test;
import util.AcoesBolsaUtil;

import java.text.ParseException;

import static org.junit.Assert.*;

public class DividendYeldBoTest {

    @Test
    public void getDividendYeld() throws ParseException {
        System.out.println(DividendYeldBo.getDividendYeld(AcoesBolsaUtil.buscaAcaoPorPapel("BCFF11"), 24));
    }
}