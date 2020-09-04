package br.com.adrianorodrigues.controleacoes.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProcessDownloadBovespaCotacoesHistoricasDia.class)
class ProcessDownloadBovespaCotacoesHistoricasDiaTest {
    @Autowired
    ProcessDownloadBovespaCotacoesHistoricasDia processDownloadBovespaCotacoesHistoricasDia;

    @Test
    void execute() {
        ProcessDownloadBovespaCotacoesHistoricasDia.execute();
    }
}