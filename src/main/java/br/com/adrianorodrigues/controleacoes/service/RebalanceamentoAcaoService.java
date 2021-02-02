package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.AcaoDTO;
import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.RebalanceamentoAcaoRepository;
import br.com.adrianorodrigues.controleacoes.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RebalanceamentoAcaoService {

    private RebalanceamentoAcaoRepository rebalanceamentoAcaoRepository;
    private CotacaoAtualService cotacaoAtualService;
    private CarteiraService carteiraService;

    @Autowired
    public RebalanceamentoAcaoService(RebalanceamentoAcaoRepository rebalanceamentoAcaoRepository, CotacaoAtualService cotacaoAtualService, CarteiraService carteiraService) {
        this.rebalanceamentoAcaoRepository = rebalanceamentoAcaoRepository;
        this.cotacaoAtualService = cotacaoAtualService;
        this.carteiraService = carteiraService;
    }

    public void save(RebalanceamentoAcao rebalanceamentoAcao) {
        rebalanceamentoAcaoRepository.save(rebalanceamentoAcao);
    }

    public void delete(Long id) {
        rebalanceamentoAcaoRepository.deleteById(id);
    }

    public List<RebalanceamentoAcao> findAllByUsuario(Long idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoRepository.findAllByUsuario(usuario);
        double totalPercentual = 0d;
        double totalNotas = 0d;
        for(RebalanceamentoAcao rebalanceamentoAcao : rebalanceamentoAcaoList){
            totalPercentual += rebalanceamentoAcao.getPercentual() == null ? 0 : rebalanceamentoAcao.getPercentual();
            totalNotas += rebalanceamentoAcao.getPercentual() != null ? 0 : rebalanceamentoAcao.getNota();
        }
        for(RebalanceamentoAcao rebalanceamentoAcao : rebalanceamentoAcaoList){
            if(rebalanceamentoAcao.getPercentual() == null)
                rebalanceamentoAcao.setPercentual((rebalanceamentoAcao.getNota()/totalNotas)*(100-totalPercentual));
        }
        return rebalanceamentoAcaoList;
    }

    public CarteiraDTO getCarteiraParaRebalanceamento(Long idUsuario) {
        CarteiraDTO carteiraDTO = carteiraService.getCarteira(idUsuario);
        carteiraDTO
                .getAcoes()
                .addAll(
                        getAcoesAusentesCarteira(
                                findAllByUsuario(idUsuario),
                                carteiraService.getAcoes(Usuario.builder().id(idUsuario).build())
                        )
                );
        return carteiraDTO;
    }

    private List<AcaoDTO> getAcoesAusentesCarteira(List<RebalanceamentoAcao> rebalanceamentoAcaoList, Map<String, AcaoDTO> acoes) {
        return rebalanceamentoAcaoList
                .stream()
                .filter(rebalanceamentoAcao -> !acoes.containsKey(rebalanceamentoAcao.getAcao().getPapel()))
                .map(rebalanceamentoAcao -> {
                    AcaoDTO acaoDTO = new AcaoDTO();
                    acaoDTO.setId(rebalanceamentoAcao.getAcao().getId());
                    acaoDTO.setPapel(rebalanceamentoAcao.getAcao().getPapel());
                    acaoDTO.setPrecoMedio(0d);
                    acaoDTO.setQuantidade(0l);
                    try {
                        acaoDTO.setValor(cotacaoAtualService.getCotacaoAtual(acaoDTO.getPapel()).getCotacao());
                    } catch (IOException e) {
                        LogUtil.getLogger().error("Erro ao buscar cotacao atual", e);
                    }
                    acaoDTO.setPercentualRebalanceamento(rebalanceamentoAcao.getPercentual());
                    return acaoDTO;
                }).collect(Collectors.toList());
    }

}
