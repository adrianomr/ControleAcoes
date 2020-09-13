package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.service.transacao.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles("test")
class TransacaoServiceTest {
    @Autowired
    TransacaoService transacaoService;
    @Autowired
    TransacaoRepository transacaoRepository;

    @Test
    void compraAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setIdUsuario(1l);
        transacaoDTO.setPapel("BCFF11");
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.COMPRA);
        List<Transacao> transacaoList = transacaoRepository.findAll();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void compraAcaoQuandoAcaoNaoExistirInsereAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setIdUsuario(1l);
        transacaoDTO.setPapel("ITUB3");
        transacaoDTO.setValor(90d);
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.COMPRA);
        List<Transacao> transacaoList = transacaoRepository.findAll();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void vendaAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setIdUsuario(1l);
        transacaoDTO.setPapel("BCFF11");
        transacaoDTO.setValor(90d);
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.VENDA);
        List<Transacao> transacaoList = transacaoRepository.findAll();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void vendaAcaoQuandoAcaoNaoExistirInsereAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setIdUsuario(1l);
        transacaoDTO.setPapel("ITUB3");
        transacaoDTO.setValor(90d);
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.VENDA);
        List<Transacao> transacaoList = transacaoRepository.findAll();
        assertEquals(1, transacaoList.size());
    }
}