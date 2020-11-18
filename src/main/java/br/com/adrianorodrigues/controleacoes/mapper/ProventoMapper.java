package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.ProventoDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Provento;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ProventoMapper {
    Provento provento;

    public static ProventoMapper from(ProventoDTO proventoDTO, Acao acao) {
        Provento provento = Provento
                .builder()
                .id(proventoDTO.getId())
                .dataPagamento(proventoDTO.getData())
                .acao(acao)
                .valor(BigDecimal.valueOf(proventoDTO.getValor()))
                .build();
        return ProventoMapper
                .builder()
                .provento(provento)
                .build();
    }

    public Provento map() {
        return provento;
    }
}
