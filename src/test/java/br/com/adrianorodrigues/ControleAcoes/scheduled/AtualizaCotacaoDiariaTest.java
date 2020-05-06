package br.com.adrianorodrigues.ControleAcoes.scheduled;

import br.com.adrianorodrigues.ControleAcoes.ControleAcoesApplication;
import br.com.adrianorodrigues.ControleAcoes.dto.StatusInicializacaoSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles("test")
class AtualizaCotacaoDiariaTest {
    @Autowired
    AtualizaCotacaoDiaria atualizaCotacaoDiaria;

    @Test
    public void atualizaCotacaoDiariaTest() {
        StatusInicializacaoSingleton.getSingleton().setInicializou();
        atualizaCotacaoDiaria.atualizaCotacoesDiarias();
    }
}