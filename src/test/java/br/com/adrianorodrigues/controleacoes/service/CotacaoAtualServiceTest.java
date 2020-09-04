package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.CotacaoAtualDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest(classes = CotacaoAtualService.class)
class CotacaoAtualServiceTest {
    @Autowired
    CotacaoAtualService cotacaoAtualService;

    @Test
    void getCotacaoAtual() throws IOException {
        String cotacao = "BCFF11";
        CotacaoAtualDTO cotacaoAtualDTO = cotacaoAtualService.getCotacaoAtual(cotacao);
        Assert.notNull(cotacaoAtualDTO);
        Assertions.assertEquals(cotacaoAtualDTO.getPapel(), cotacao);
        Assert.notNull(cotacaoAtualDTO.getCotacao());
    }
}