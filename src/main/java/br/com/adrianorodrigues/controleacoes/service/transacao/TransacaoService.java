package br.com.adrianorodrigues.controleacoes.service.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        Usuario usuario = usuarioRepository
                .findById(transacaoDTO.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não existe"));
        Transacao transacao = new Transacao();
        transacao.setData(transacaoDTO.getData());
        transacao.setAcao(acao);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setQuantidade(transacaoDTO.getQuantidade());
        transacao.setValor(BigDecimal.valueOf(transacaoDTO.getValor()));
        transacao.setUsuario(usuario);
        transacaoRepository.save(transacao);
    }

    public List<TransacaoDTO> findAllTransacaoes() {
        return transacaoRepository.findAll().stream().map(transacao -> {
            TransacaoDTO transacaoDTO = new TransacaoDTO();
            transacaoDTO.setId(transacao.getId());
            transacaoDTO.setPapel(transacao.getAcao().getPapel());
            int fator = transacao.getTipoTransacao().equals(TipoTransacao.COMPRA) ? 1 : -1;
            transacaoDTO.setValor(fator * transacao.getValor().doubleValue());
            transacaoDTO.setIdUsuario(transacao.getUsuario().getId());
            transacaoDTO.setQuantidade(transacao.getQuantidade());
            transacaoDTO.setData(transacao.getData());
            return transacaoDTO;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Transacao transacao = new Transacao();
        transacao.setId(id);
        transacaoRepository.delete(transacao);
    }
}
