package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.AcaoDTO;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.repository.view.AcaoDtoView;
import lombok.Builder;

@Builder
public class AcaoDtoMapper {
    AcaoDTO acaoDTO;

    public static AcaoDtoMapper from(AcaoDtoView acaoDtoView, RebalanceamentoAcao rebalanceamentoAcao) {
        AcaoDTO acaoDTO = AcaoDTO
                .builder()
                .id(acaoDtoView.getId())
                .papel(acaoDtoView.getPapel())
                .quantidade(acaoDtoView.getQuantidade())
                .percentualRebalanceamento(rebalanceamentoAcao == null ? 0 : rebalanceamentoAcao.getPercentual())
                .nota(rebalanceamentoAcao == null ? 0 : rebalanceamentoAcao.getNota())
                .build();
        return AcaoDtoMapper
                .builder()
                .acaoDTO(acaoDTO)
                .build();
    }

    public AcaoDTO map() {
        return acaoDTO;
    }
}
