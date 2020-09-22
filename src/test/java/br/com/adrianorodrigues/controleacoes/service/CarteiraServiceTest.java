package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.dto.CotacaoAtualDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarteiraServiceTest {

    @Mock
    TransacaoRepository transacaoRepository;
    @Mock
    CotacaoAtualService cotacaoAtualService;
    @Mock
    RebalanceamentoAcaoService rebalanceamentoAcaoService;
    @InjectMocks
    CarteiraService carteiraService;
    Usuario usuario;
    Transacao compraKnri;
    Transacao novaCompraKnri;
    Acao knri;
    CotacaoAtualDTO cotacaoKnri;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1l);
        knri = new Acao();
        knri.setId(1l);
        knri.setPapel("KNRI11");
        compraKnri = new Transacao();
        compraKnri.setId(1l);
        compraKnri.setUsuario(usuario);
        compraKnri.setValor(new BigDecimal(50.25));
        compraKnri.setTipoTransacao(TipoTransacao.COMPRA);
        compraKnri.setData(LocalDateTime.now());
        compraKnri.setQuantidade(3);
        compraKnri.setAcao(knri);
        novaCompraKnri = new Transacao();
        novaCompraKnri.setId(2l);
        novaCompraKnri.setUsuario(usuario);
        novaCompraKnri.setValor(new BigDecimal(50.25));
        novaCompraKnri.setTipoTransacao(TipoTransacao.COMPRA);
        novaCompraKnri.setData(LocalDateTime.now());
        novaCompraKnri.setQuantidade(3);
        novaCompraKnri.setAcao(knri);
        cotacaoKnri = new CotacaoAtualDTO();
        cotacaoKnri.setPapel("KNRI11");
        cotacaoKnri.setCotacao(60.65);
    }

    @Test
    void getCarteira() throws IOException {
        List<Transacao> transacaoList = new ArrayList<>();
        transacaoList.add(compraKnri);
        transacaoList.add(novaCompraKnri);
        Mockito.when(rebalanceamentoAcaoService.findAllByUsuario(1l)).thenReturn(null);
        Mockito.when(transacaoRepository.findAllByUsuario(usuario)).thenReturn(transacaoList);
        Mockito.when(cotacaoAtualService.getCotacaoAtual("KNRI11")).thenReturn(cotacaoKnri);
        CarteiraDTO carteiraDTO = carteiraService.getCarteira(1l);
        Assertions.assertEquals(
                301.5,
                carteiraDTO.getValorInvestido(),
                "Valor investido deve ser o acumulado das transações");
    }

    @Test
    void getCarteiraQuandoNaoEncontrarCotacaoNaoPodeLancarException() throws IOException {
        List<Transacao> transacaoList = new ArrayList<>();
        transacaoList.add(compraKnri);
        transacaoList.add(novaCompraKnri);
        Mockito.when(rebalanceamentoAcaoService.findAllByUsuario(1l)).thenReturn(null);
        Mockito.when(transacaoRepository.findAllByUsuario(usuario)).thenReturn(transacaoList);
        Mockito.when(cotacaoAtualService.getCotacaoAtual("KNRI11")).thenThrow(new IOException());
        CarteiraDTO carteiraDTO = carteiraService.getCarteira(1l);
        Assertions.assertEquals(
                301.5,
                carteiraDTO.getValorInvestido(),
                "Valor investido deve ser o acumulado das transações");
        Assertions.assertEquals(
                0,
                carteiraDTO.getValorAtual(),
                "Valor investido deve ser zero pois nao achou preco");
    }

    @Test
    void getCarteiraWhenRebalanceamentoCreatedReturnInfoRebalanceamento() throws IOException {
        List<Transacao> transacaoList = new ArrayList<>();
        transacaoList.add(compraKnri);
        transacaoList.add(novaCompraKnri);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = new ArrayList<>();
        RebalanceamentoAcao rebalanceamentoAcao = new RebalanceamentoAcao();
        rebalanceamentoAcao.setId(1l);
        rebalanceamentoAcao.setPercentual(25d);
        rebalanceamentoAcao.setAcao(knri);
        rebalanceamentoAcao.setUsuario(usuario);
        rebalanceamentoAcaoList.add(rebalanceamentoAcao);
        Mockito.when(rebalanceamentoAcaoService.findAllByUsuario(1l)).thenReturn(rebalanceamentoAcaoList);
        Mockito.when(transacaoRepository.findAllByUsuario(usuario)).thenReturn(transacaoList);
        Mockito.when(cotacaoAtualService.getCotacaoAtual("KNRI11")).thenReturn(cotacaoKnri);
        CarteiraDTO carteiraDTO = carteiraService.getCarteira(1l);
        Assertions.assertEquals(
                25d,
                carteiraDTO.getAcoes().get(0).getPercentualRebalanceamento(),
                "Percentual rebalanceamento deve ser igual ao cadastrado");
    }
}