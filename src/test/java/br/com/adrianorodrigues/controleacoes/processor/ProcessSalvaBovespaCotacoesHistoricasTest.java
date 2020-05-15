package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.dto.HashMapAcaoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProcessSalvaBovespaCotacoesHistoricasTest {
    @Autowired
    ProcessSalvaBovespaCotacoesHistoricas processSalvaBovespaCotacoesHistoricas;

    @Test
    void execute() {
//        ProcessSalvaBovespaCotacoesHistoricas processSalvaBovespaCotacoesHistoricas = new ProcessSalvaBovespaCotacoesHistoricas();
        processSalvaBovespaCotacoesHistoricas.execute();
        System.out.println(HashMapAcaoDto.getHashAcaoDto().get("ITSA4"));
        System.out.println(HashMapAcaoDto.getHashAcaoDto().get("ITSA4F"));
        System.out.println(HashMapAcaoDto.getHashAcaoDto().get("itsa4"));
    }
}