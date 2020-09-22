package br.com.adrianorodrigues.controleacoes.client;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import br.com.adrianorodrigues.controleacoes.dto.alphavantage.AlphavantageGlobalQuoteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.logging.Logger;
@Disabled
@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles("test")
class AlphavantageClientTest {
    @Autowired
    AlphavantageClient alphavantageClient;

    @Disabled("Teste da api do alphavantage, rodar so quando necessario")
    @Test
    void getCotacao() {
        AlphavantageGlobalQuoteDTO alphavantageDTO = alphavantageClient.getCotacao("ITSA4");
        Logger.getGlobal().info(alphavantageDTO.toString());
        Assertions.assertNotEquals(null, alphavantageDTO);
        Assertions.assertNotEquals(null, alphavantageDTO.getCotacao());
    }
}