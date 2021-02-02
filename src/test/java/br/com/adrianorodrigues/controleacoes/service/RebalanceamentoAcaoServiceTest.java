package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.repository.RebalanceamentoAcaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RebalanceamentoAcaoServiceTest {

    RebalanceamentoAcaoService rebalanceamentoAcaoService;
    RebalanceamentoAcaoRepository rebalanceamentoAcaoRepository;
    CotacaoAtualService cotacaoAtualService;
    CarteiraService carteiraService;
    private List<RebalanceamentoAcao> list = Arrays.asList(
            RebalanceamentoAcao
            .builder()
            .nota(10D)
            .percentual(50D)
            .build(),
            RebalanceamentoAcao
            .builder()
            .nota(10D)
            .build()
    );

    @BeforeEach
    void setUp() {
        rebalanceamentoAcaoRepository = Mockito.mock(RebalanceamentoAcaoRepository.class);
        cotacaoAtualService = Mockito.mock(CotacaoAtualService.class);
        carteiraService = Mockito.mock(CarteiraService.class);
        rebalanceamentoAcaoService = new RebalanceamentoAcaoService(
                rebalanceamentoAcaoRepository, cotacaoAtualService, carteiraService);
    }

    @Test
    void findAllByUsuario() {
        Mockito
                .when(rebalanceamentoAcaoRepository.findAllByUsuario(Mockito.any()))
                .thenReturn(list);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService.findAllByUsuario(1L);
        assertEquals(50D, rebalanceamentoAcaoList.get(0).getPercentual());
    }

    @Test
    void findAllByUsuarioWhenPercentualIsNullShouldCalculateUtilizingNota() {
        Mockito
                .when(rebalanceamentoAcaoRepository.findAllByUsuario(Mockito.any()))
                .thenReturn(list);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService.findAllByUsuario(1L);
        assertEquals(50D, rebalanceamentoAcaoList.get(1).getPercentual());
    }

    @Test
    void findAllByUsuarioWhenOnlyPercentualShouldReturnPercentual() {
        List<RebalanceamentoAcao> list = Arrays.asList(
                RebalanceamentoAcao
                        .builder()
                        .nota(10D)
                        .percentual(50D)
                        .build()
        );
        Mockito
                .when(rebalanceamentoAcaoRepository.findAllByUsuario(Mockito.any()))
                .thenReturn(list);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService.findAllByUsuario(1L);
        assertEquals(50D, rebalanceamentoAcaoList.get(0).getPercentual());
    }

    @Test
    void findAllByUsuarioWhenOnlyNotaShouldReturnCalculateUtilizingNota() {
        List<RebalanceamentoAcao> list = Arrays.asList(
                RebalanceamentoAcao
                        .builder()
                        .nota(10D)
                        .build()
        );
        Mockito
                .when(rebalanceamentoAcaoRepository.findAllByUsuario(Mockito.any()))
                .thenReturn(list);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService.findAllByUsuario(1L);
        assertEquals(100D, rebalanceamentoAcaoList.get(0).getPercentual());
    }
}