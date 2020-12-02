package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.ChartSubgrupoDto;
import br.com.adrianorodrigues.controleacoes.dto.GrupoAcaoDto;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.exception.ValidationException;
import br.com.adrianorodrigues.controleacoes.interfaces.CrudService;
import br.com.adrianorodrigues.controleacoes.mapper.GrupoAcaoDtoMapper;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import br.com.adrianorodrigues.controleacoes.repository.GrupoAcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.view.AcaoDtoView;
import br.com.adrianorodrigues.controleacoes.service.transacao.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GrupoAcaoService implements CrudService<GrupoAcao> {

    @Autowired
    GrupoAcaoRepository grupoAcaoRepository;
    @Autowired
    CotacaoAtualService cotacaoAtualService;
    @Autowired
    TransacaoService transacaoService;

    @Override
    public List<GrupoAcao> find() {
        return grupoAcaoRepository.findAll();
    }

    public GrupoAcao find(Long id) {
        return grupoAcaoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de ações não encontrado"));
    }

    public GrupoAcao save(GrupoAcao grupoAcao) {
        return grupoAcaoRepository.save(grupoAcao);
    }

    public void delete(Long id) {
        grupoAcaoRepository.deleteById(id);
    }

    public List<GrupoAcao> findByIdUsuario(Long idUsuario, Boolean onlyGroups, Boolean onlySubgroups) {
        if (onlyGroups && onlySubgroups)
            throw new ValidationException("Only groups e only subgroups não podem ser utilizados juntos");
        else if (Boolean.TRUE.equals(onlyGroups))
            return grupoAcaoRepository.findAllByUsuarioIdAndSubgrupoListNotNull(idUsuario);
        else if (Boolean.TRUE.equals(onlySubgroups))
            return grupoAcaoRepository.findAllByUsuarioIdAndSubgrupoListNull(idUsuario);
        return grupoAcaoRepository.findAllByUsuarioId(idUsuario);
    }

    public ChartSubgrupoDto getChartSubgrupo(Long idUsuario) {
        List<GrupoAcao> grupoAcaoList = grupoAcaoRepository.findAllByUsuarioIdAndSubgrupoListNull(idUsuario);
        List<GrupoAcaoDto> grupoAcaoDtoList = new ArrayList<>();
        List<AcaoDtoView> acaoDtoViewList = transacaoService.findAllDistinctAcaoByUsuarioIdGroupByAcao(idUsuario);
        double valorTotal = 0d;
        for (GrupoAcao grupoAcao : grupoAcaoList) {
            GrupoAcaoDto grupoAcaoDto = GrupoAcaoDtoMapper.from(grupoAcao).map();
            for (Acao acao : grupoAcao.getAcaoList()) {
                //somar preco atual por grupo
                try {
                    AcaoDtoView acaoDtoView = acaoDtoViewList
                            .stream()
                            .filter(acaoFilter -> acaoFilter.getId().equals(acao.getId()))
                            .findFirst()
                            .orElse(null);
                    acao.setValor(BigDecimal
                            .valueOf(cotacaoAtualService
                                    .getCotacaoAtual(acao.getPapel()).getCotacao()));
                    acao.setQuantidade(acaoDtoView == null ? 0 : acaoDtoView.getQuantidade()
                            .intValue());
                } catch (IOException e) {
                    acao.setValor(BigDecimal.ZERO);
                    log.info("Não foi possível encontrar a cotação do papel: {}", acao.getPapel());
                }
                double valorInvestinoAcao = (acao.getValor().doubleValue() * acao.getQuantidade());
                grupoAcaoDto.setValorInvestido(grupoAcaoDto.getValorInvestido() + valorInvestinoAcao);
                valorTotal += valorInvestinoAcao;
            }
            grupoAcaoDtoList.add(grupoAcaoDto);
        }

        return ChartSubgrupoDto
                .builder()
                .grupoAcaoList(grupoAcaoDtoList)
                .valorTotal(valorTotal)
                .build();
    }
}
