package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.AcaoDTO;
import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.dto.RiscoDto;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.mapper.AcaoDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.CarteiraMapper;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Corretora;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.CarteiraRepository;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import br.com.adrianorodrigues.controleacoes.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CarteiraService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CotacaoAtualService cotacaoAtualService;
    @Autowired
    private RebalanceamentoAcaoService rebalanceamentoAcaoService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CarteiraRepository carteiraRepository;
    @Autowired
    CorretoraService corretoraService;

    public CarteiraDTO getCarteira(Long idUsuario) {
        CarteiraDTO carteiraDTO = CarteiraDTO
                .builder()
                .build();
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        carteiraDTO.setAcoes(getAcoesDTOList(carteiraDTO, getAcoes(usuario)));
        return carteiraDTO;
    }

    private List<AcaoDTO> getAcoesDTOList(CarteiraDTO carteiraDTO, Map<String, AcaoDTO> acoes) {
        List<AcaoDTO> acaoDTOList = new ArrayList<>();
        acoes.values().stream().forEach(acaoDTO -> {
            if (acaoDTO.getQuantidade() > 0) {
                Double lucroPrejuizoAcao = (acaoDTO.getValor() - acaoDTO.getPrecoMedio()) * acaoDTO.getQuantidade();
                acaoDTO.setLucroPrejuizo(lucroPrejuizoAcao);
                acaoDTOList.add(acaoDTO);
                carteiraDTO.setLucroPrejuizo(carteiraDTO.getLucroPrejuizo() + lucroPrejuizoAcao);
                carteiraDTO.setValorAtual(carteiraDTO.getValorAtual() + (acaoDTO.getValor() * acaoDTO.getQuantidade()));
                carteiraDTO.setValorInvestido(carteiraDTO.getValorInvestido() + (acaoDTO.getPrecoMedio() * acaoDTO.getQuantidade()));
            }
        });
        return acaoDTOList;
    }

    private RebalanceamentoAcao findRebalanceamentoByAcao(List<RebalanceamentoAcao> rebalanceamentoAcaoList, Long idAcao) {
        try {
            return rebalanceamentoAcaoList
                    .stream()
                    .filter(rebalanceamentoAcao -> rebalanceamentoAcao.getAcao().getId().equals(idAcao))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            LogUtil.getLogger().info("Não foram encontrados dados de rebalanceamento");
        }
        return null;
    }

    public Map<String, AcaoDTO> getAcoes(Usuario usuario) {
        Map<String, AcaoDTO> acoes = new HashMap<>();
        List<Transacao> transacaoList = transacaoRepository.findAllByUsuario(usuario);
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService
                .findAllByUsuario(usuario.getId());
        transacaoList.stream().forEach((Transacao transacao) -> {
            AcaoDTO acaoDTO = new AcaoDTO();
            int fatorTransacao = transacao.getTipoTransacao().equals(TipoTransacao.COMPRA) ? 1 : -1;
            long quantidade = fatorTransacao * transacao.getQuantidade();
            double valor = transacao.getValor().doubleValue();
            if (acoes.containsKey(transacao.getAcao().getPapel())) {
                acaoDTO = acoes.get(transacao.getAcao().getPapel());
                acaoDTO.setPrecoMedio(calculatePrecoMedio(acaoDTO, quantidade, valor));
                acaoDTO.setQuantidade(acaoDTO.getQuantidade() + quantidade);
            } else {
                acaoDTO.setId(transacao.getAcao().getId());
                acaoDTO.setPapel(transacao.getAcao().getPapel());
                acaoDTO.setQuantidade(quantidade);
                acaoDTO.setPrecoMedio(valor);
                RebalanceamentoAcao rebalanceamentoAcao = findRebalanceamentoByAcao(rebalanceamentoAcaoList, acaoDTO.getId());
                double percentual = rebalanceamentoAcao == null || rebalanceamentoAcao.getPercentual() == null ? 0 : rebalanceamentoAcao.getPercentual();
                acaoDTO.setPercentualRebalanceamento(percentual);
                acaoDTO = getValorAcaoDto(acaoDTO);
            }
            acoes.put(transacao.getAcao().getPapel(), acaoDTO);
        });
        return acoes;
    }

    private Double calculatePrecoMedio(AcaoDTO acaoDTO, long quantidade, double valor) {
        if(quantidade + acaoDTO.getQuantidade() == 0)
            return 0D;
        if(quantidade <= 0)
            return acaoDTO.getPrecoMedio();
        return ((acaoDTO.getPrecoMedio() * acaoDTO.getQuantidade()) + (valor * quantidade)) / (quantidade + acaoDTO.getQuantidade());
    }

    private AcaoDTO getValorAcaoDto(AcaoDTO acaoDTO) {
        try {
            acaoDTO.setValor(cotacaoAtualService.getCotacaoAtual(acaoDTO.getPapel()).getCotacao());
        } catch (IOException e) {
            acaoDTO.setValor(0d);
            LogUtil.getLogger().error("Erro ao buscar cotacao atual", e);
        }
        return acaoDTO;
    }

    public Set<Carteira> findCarteiraByUsuario(Long idUsuario) {
        return carteiraRepository.findAllByUsuarioId(idUsuario);
    }

    public Carteira save(CarteiraDTO carteiraDTO) {
        Usuario usuario = usuarioRepository.findById(carteiraDTO.getIdUsuario()).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
        Corretora corretora = corretoraService.find(carteiraDTO.getIdCorretora());
        return save(CarteiraMapper.from(carteiraDTO, corretora, usuario).map());
    }

    public Carteira save(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    public void delete(Long id) {
        carteiraRepository.deleteById(id);
    }

    public Carteira findCarteiraByCorretoraAndUsuario(Long idCorretora, Long idUsuario) {
        return carteiraRepository
                .findByCorretoraIdAndUsuarioId(idCorretora, idUsuario)
                .orElseGet(() ->
                        save(CarteiraDTO
                                .builder()
                                .idCorretora(idCorretora)
                                .idUsuario(idUsuario)
                                .build()));
    }

    public Carteira findCarteiraById(Long idCarteira) {
        return carteiraRepository
                .findById(idCarteira)
                .orElseThrow(() -> new ResourceNotFoundException("Carteira nao encontrada"));
    }

    public RiscoDto getRisco(Long idUsuario, Double riscoBaixoMedio, Double riscoMedioAlto) {
        RiscoDto riscoDto = new RiscoDto();
        List<RebalanceamentoAcao> rebalanceamentoAcaoList = rebalanceamentoAcaoService
                .findAllByUsuario(idUsuario);
        List<AcaoDTO> acaoDTOList = transacaoRepository
                .findAllDistinctAcaoByUsuarioIdGroupByAcao(idUsuario)
                .stream()
                .filter(acaoDtoView -> acaoDtoView.getQuantidade() > 0)
                .map(acaoDtoView -> {
                    RebalanceamentoAcao rebalanceamentoAcao = findRebalanceamentoByAcao(rebalanceamentoAcaoList, acaoDtoView.getId());
                    AcaoDTO acaoDTO = AcaoDtoMapper
                            .from(acaoDtoView,
                                    rebalanceamentoAcao)
                            .map();
                    acaoDTO = getValorAcaoDto(acaoDTO);
                    double valor = acaoDTO.getQuantidade() * acaoDTO.getValor();
                    if (acaoDTO.getNota() > riscoMedioAlto)
                        riscoDto.setTotalRiscoBaixo(riscoDto.getTotalRiscoBaixo() + valor);
                    else if (acaoDTO.getNota() < riscoBaixoMedio)
                        riscoDto.setTotalRiscoAlto(riscoDto.getTotalRiscoAlto() + valor);
                    else
                        riscoDto.setTotalRiscoMedio(riscoDto.getTotalRiscoMedio() + valor);
                    riscoDto.setValorTotal(riscoDto.getValorTotal() + valor);
                    return acaoDTO;
                })
                .collect(Collectors.toList());
        riscoDto.setAcaoList(acaoDTOList);
        return riscoDto;
    }
}
