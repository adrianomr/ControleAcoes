package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.AcaoDTO;
import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CarteiraService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CotacaoAtualService cotacaoAtualService;

    public CarteiraDTO getCarteira(Long idUsuario) {
        CarteiraDTO carteiraDTO = CarteiraDTO
                .builder()
                .build();
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        List<Transacao> transacaoList = transacaoRepository.findAllByUsuario(usuario);
        Map<String, AcaoDTO> acoes = new HashMap<>();
        transacaoList.stream().forEach((Transacao transacao) -> {
            AcaoDTO acaoDTO = new AcaoDTO();
            int fatorTransacao = transacao.getTipoTransacao().equals(TipoTransacao.COMPRA) ? 1 : -1;
            long quantidade = fatorTransacao * transacao.getQuantidade();
            double valor = quantidade * transacao.getValor().doubleValue();
            if (acoes.containsKey(transacao.getAcao().getPapel())) {
                acaoDTO = acoes.get(transacao.getAcao().getPapel());
                acaoDTO.setQuantidade(acaoDTO.getQuantidade() + quantidade);
                acaoDTO.setPrecoMedio(acaoDTO.getPrecoMedio() + valor);
            } else {
                acaoDTO.setId(transacao.getAcao().getId());
                acaoDTO.setPapel(transacao.getAcao().getPapel());
                acaoDTO.setQuantidade(quantidade);
                acaoDTO.setPrecoMedio(valor);
                try {
                    acaoDTO.setValor(cotacaoAtualService.getCotacaoAtual(acaoDTO.getPapel()).getCotacao());
                } catch (IOException e) {
                    LogUtil.getLogger().error("Erro ao buscar cotacao atual", e);
                }
            }
            acoes.put(transacao.getAcao().getPapel(), acaoDTO);
        });
        List<AcaoDTO> acaoDTOList = new ArrayList<>();
        acoes.values().stream().forEach(acaoDTO -> {
            if (acaoDTO.getQuantidade() > 0) {
                acaoDTO.setPrecoMedio(acaoDTO.getPrecoMedio() / acaoDTO.getQuantidade());
                acaoDTOList.add(acaoDTO);
                Double lucroPrejuizoAcao = (acaoDTO.getValor() - acaoDTO.getPrecoMedio()) * acaoDTO.getQuantidade();
                carteiraDTO.setLucroPrejuizo(carteiraDTO.getLucroPrejuizo() + lucroPrejuizoAcao);
                carteiraDTO.setValorAtual(carteiraDTO.getValorAtual() + (acaoDTO.getValor() * acaoDTO.getQuantidade()));
                carteiraDTO.setValorInvestido(carteiraDTO.getValorInvestido() + (acaoDTO.getPrecoMedio() * acaoDTO.getQuantidade()));
            }
        });
        carteiraDTO.setAcoes(acaoDTOList);
        return carteiraDTO;
    }
}
