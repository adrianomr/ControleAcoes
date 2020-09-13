package br.com.adrianorodrigues.controleacoes.service.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import br.com.adrianorodrigues.controleacoes.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TransacaoService {
    @Autowired
    AcaoService acaoService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TransacaoRepository transacaoRepository;

    private Acao findOrCreateAcao(String papel) {
        Acao acao = acaoService.findAcaoByPapel(papel);
        if (acao == null) {
            acao = new Acao();
            acao.setPapel(papel);
            acaoService.insertAcao(acao);
        }
        return acao;
    }

    public void saveTrasacao(TransacaoDTO transacaoDTO, TipoTransacao tipoTransacao) {
        Acao acao = findOrCreateAcao(transacaoDTO.getPapel());
        Transacao transacao = new Transacao();
        transacao.setData(transacaoDTO.getData());
        transacao.setAcao(acao);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setQuantidade(transacaoDTO.getQuantidade());
        transacao.setValor(BigDecimal.valueOf(transacaoDTO.getValor()));
        transacao.setUsuario(usuarioRepository.getOne(transacaoDTO.getIdUsuario()));
        transacaoRepository.save(transacao);
    }

    public List<Transacao> findAllTransacaoes() {
        return transacaoRepository.findAll();
    }

    public void delete(Long id) {
        transacaoRepository.deleteById(id);
    }
}
