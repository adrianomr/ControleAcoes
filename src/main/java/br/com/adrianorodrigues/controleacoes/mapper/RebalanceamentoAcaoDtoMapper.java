package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.RebalanceamentoAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import lombok.Builder;

@Builder
public class RebalanceamentoAcaoDtoMapper {
    RebalanceamentoAcaoDto rebalanceamentoAcaoDto;

    public static RebalanceamentoAcaoDtoMapper from(RebalanceamentoAcao rebalanceamentoAcao) {
        RebalanceamentoAcaoDto rebalanceamentoAcaoDto = RebalanceamentoAcaoDto
                .builder()
                .id(rebalanceamentoAcao.getId())
                .idUsuario(rebalanceamentoAcao.getUsuario().getId())
                .nota(rebalanceamentoAcao.getNota())
                .papel(rebalanceamentoAcao.getAcao().getPapel())
                .percentual(rebalanceamentoAcao.getPercentual())
                .build();
        return RebalanceamentoAcaoDtoMapper
                .builder()
                .rebalanceamentoAcaoDto(rebalanceamentoAcaoDto)
                .build();
    }

    public RebalanceamentoAcaoDto map() {
        return rebalanceamentoAcaoDto;
    }
}
