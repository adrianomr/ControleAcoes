package br.com.adrianorodrigues.controleacoes.processor;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessDownloadBovespaCotacoesHistoricasTest {

    @Test
    void execute() throws InterruptedException {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(anoAtual - 1986, ProcessDownloadBovespaCotacoesHistoricas.execute());
    }
}