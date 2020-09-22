package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.exception.ValidationException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class RebalanceamentoAcaoServiceIntegrationTest {
    @Autowired
    RebalanceamentoAcaoService rebalanceamentoAcaoService;
    @Autowired
    UsuarioRepository usuarioRepository;
    Usuario usuario;
    @Autowired
    AcaoRepository acaoRepository;
    Acao acao;

    @BeforeEach
    void setUp() {
        usuario = usuarioRepository.findAll().get(0);
        acao = acaoRepository.findAll().get(0);
    }

    @Test
    void saveQuandoPercentualENotaInformadosDeveLancarExcecao() {
        RebalanceamentoAcao rebalanceamentoAcao = new RebalanceamentoAcao();
        rebalanceamentoAcao.setUsuario(usuario);
        rebalanceamentoAcao.setAcao(acao);
        rebalanceamentoAcao.setPercentual(25d);
        rebalanceamentoAcao.setNota(10d);
        assertThrows(ValidationException.class,
                () -> rebalanceamentoAcaoService.save(rebalanceamentoAcao));
    }

    @Test
    void saveQuandoApenasNotaInformadaDeveSalvar() {
        RebalanceamentoAcao rebalanceamentoAcao = new RebalanceamentoAcao();
        rebalanceamentoAcao.setUsuario(usuario);
        rebalanceamentoAcao.setAcao(acao);
        rebalanceamentoAcao.setPercentual(null);
        rebalanceamentoAcao.setNota(10d);
        assertDoesNotThrow(() -> rebalanceamentoAcaoService.save(rebalanceamentoAcao));
    }

    @Test
    void saveQuandoApenasPercentualInformadoDeveSalvar() {
        RebalanceamentoAcao rebalanceamentoAcao = new RebalanceamentoAcao();
        rebalanceamentoAcao.setUsuario(usuario);
        rebalanceamentoAcao.setAcao(acao);
        rebalanceamentoAcao.setPercentual(null);
        rebalanceamentoAcao.setNota(10d);
        assertDoesNotThrow(() -> rebalanceamentoAcaoService.save(rebalanceamentoAcao));
    }
}