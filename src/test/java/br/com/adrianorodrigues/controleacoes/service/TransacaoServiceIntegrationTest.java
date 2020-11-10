package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import br.com.adrianorodrigues.controleacoes.service.transacao.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class TransacaoServiceIntegrationTest {
    @Autowired
    TransacaoService transacaoService;
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = usuarioRepository.findAll().get(0);
    }

    @Test
    void compraAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = TransacaoDTO.builder().build();
        transacaoDTO.setIdUsuario(usuario.getId());
        transacaoDTO.setPapel("BCFF11");
        transacaoDTO.setData(LocalDateTime.now());
        transacaoDTO.setValor(90d);
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.COMPRA);
        List<TransacaoDTO> transacaoList = transacaoService.findAllTransacaoes();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void compraAcaoQuandoAcaoNaoExistirInsereAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = TransacaoDTO.builder().build();
        transacaoDTO.setIdUsuario(usuario.getId());
        transacaoDTO.setPapel("ITUB3");
        transacaoDTO.setValor(90d);
        transacaoDTO.setData(LocalDateTime.now());
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.COMPRA);
        List<TransacaoDTO> transacaoList = transacaoService.findAllTransacaoes();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void vendaAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = TransacaoDTO.builder().build();
        transacaoDTO.setIdUsuario(usuario.getId());
        transacaoDTO.setPapel("BCFF11");
        transacaoDTO.setValor(90d);
        transacaoDTO.setData(LocalDateTime.now());
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.VENDA);
        List<TransacaoDTO> transacaoList = transacaoService.findAllTransacaoes();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void vendaAcaoQuandoAcaoNaoExistirInsereAcao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = TransacaoDTO.builder().build();
        transacaoDTO.setIdUsuario(usuario.getId());
        transacaoDTO.setPapel("ITUB3");
        transacaoDTO.setValor(90d);
        transacaoDTO.setData(LocalDateTime.now());
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.VENDA);
        List<TransacaoDTO> transacaoList = transacaoService.findAllTransacaoes();
        assertEquals(1, transacaoList.size());
    }

    @Test
    void deleteTransacao() {
        transacaoRepository.deleteAll();
        TransacaoDTO transacaoDTO = TransacaoDTO.builder().build();
        transacaoDTO.setIdUsuario(usuario.getId());
        transacaoDTO.setPapel("BCFF11");
        transacaoDTO.setData(LocalDateTime.now());
        transacaoDTO.setValor(90d);
        transacaoService.saveTrasacao(transacaoDTO, TipoTransacao.COMPRA);
        List<TransacaoDTO> transacaoList = transacaoService.findAllTransacaoes();
        transacaoService.delete(transacaoList.get(0).getId());
        transacaoList = transacaoService.findAllTransacaoes();
        assertEquals(0, transacaoList.size());
    }
}