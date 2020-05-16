package br.com.adrianorodrigues.controleacoes.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProcessSalvaBovespaCotacoesHistoricasSequencialTest {
    @Autowired
    ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial;

    @Autowired
    ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial;

    @Test
    void execute() {
        processSalvaAcoesHistoricasSequencial.execute();
        processSalvaBovespaCotacoesHistoricasSequencial.execute();
    }
}