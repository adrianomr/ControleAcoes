package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransacaoService {
    @Autowired
    AcaoService acaoService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TransacaoRepository transacaoRepository;

    public void compraAcao(TransacaoDTO transacaoDTO) {
        Acao acao = findOrCreateAcao(transacaoDTO.getPapel());
        Transacao transacao = new Transacao();
        transacao.setAcao(acao);
        transacao.setTipoTransacao(TipoTransacao.COMPRA);
        transacao.setValor(BigDecimal.valueOf(transacaoDTO.getValor()));
        transacao.setUsuario(usuarioRepository.getOne(transacaoDTO.getIdUsuario()));
        transacaoRepository.save(transacao);
    }

    private Acao findOrCreateAcao(String papel) {
        Acao acao = acaoService.findAcaoByPapel(papel);
        if (acao == null) {
            acao = new Acao();
            acao.setPapel(papel);
            acaoService.insertAcao(acao);
        }
        return acao;
    }
}
