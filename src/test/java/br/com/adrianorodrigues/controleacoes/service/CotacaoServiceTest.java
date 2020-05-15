package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.CotacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class CotacaoServiceTest {

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    CotacaoRepository cotacaoRepository;

    @Autowired
    CotacaoService cotacaoService;

    @Test
    void insertListCotacao() {
        Acao acao = new Acao();
        acao.setValor(new BigDecimal(10));
        acao.setQuantidade(10);
        acao.setPapel("TESTE");
        acao.setNomeEmpresa("EMPRESA TESTE");
        acaoRepository.saveAndFlush(acao);
        Cotacao cotacao = new Cotacao();
        cotacao.setData(new Date());
        cotacao.setValorFechamento(10d);
        cotacao.setAcao(acao);
        cotacaoRepository.saveAndFlush(cotacao);
        System.out.println(cotacao);
    }

    @Test
    void getCotacaoAtual() {
        System.out.println(cotacaoService.getCotacaoAtual(4052200l));
    }
}