package br.com.adrianorodrigues.controleacoes.scheduled;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import br.com.adrianorodrigues.controleacoes.dto.StatusInicializacaoSingleton;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled("Teste pois rotina não está sendo usada, precisa de ajustes")
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