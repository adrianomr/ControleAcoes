package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.ProventoDTO;
import br.com.adrianorodrigues.controleacoes.model.Provento;
import lombok.Builder;

@Builder
public class ProventoDtoMapper {
    ProventoDTO proventoDTO;

    public static ProventoDtoMapper from(Provento provento) {
        ProventoDTO proventoDTO = ProventoDTO
                .builder()
                .id(provento.getId())
                .data(provento.getDataPagamento())
                .papel(provento.getAcao().getPapel())
                .valor(provento.getValor().doubleValue())
                .build();
        return ProventoDtoMapper
                .builder()
                .proventoDTO(proventoDTO)
                .build();
    }

    public ProventoDTO map() {
        return proventoDTO;
    }
}
