package br.com.adrianorodrigues.ControleAcoes.processor;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ProcessBovespaCotacoesHistoricasTest {

    @Test
    void execute() throws InterruptedException {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(anoAtual - 1986, ProcessBovespaCotacoesHistoricas.execute());
    }
}