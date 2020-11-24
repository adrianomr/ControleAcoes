package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.RebalanceamentoAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.Builder;

@Builder
public class RebalanceamentoAcaoMapper {
    RebalanceamentoAcao rebalanceamentoAcao;

    public static RebalanceamentoAcaoMapper from(RebalanceamentoAcaoDto rebalanceamentoAcaoDto, Acao acao, Usuario usuario) {
        RebalanceamentoAcao rebalanceamentoAcao = RebalanceamentoAcao
                .builder()
                .id(rebalanceamentoAcaoDto.getId())
                .acao(acao)
                .usuario(usuario)
                .nota(rebalanceamentoAcaoDto.getNota())
                .percentual(rebalanceamentoAcaoDto.getPercentual())
                .build();
        return RebalanceamentoAcaoMapper
                .builder()
                .rebalanceamentoAcao(rebalanceamentoAcao)
                .build();
    }

    public RebalanceamentoAcao map() {
        return rebalanceamentoAcao;
    }
}
