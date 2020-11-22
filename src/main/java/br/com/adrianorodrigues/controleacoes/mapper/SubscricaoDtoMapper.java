package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.SubscricaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import lombok.Builder;

@Builder
public class SubscricaoDtoMapper {
    SubscricaoDTO subscricaoDTO;

    public static SubscricaoDtoMapper from(Subscricao subscricao) {
        SubscricaoDTO subscricaoDTO = SubscricaoDTO
                .builder()
                .id(subscricao.getId())
                .data(subscricao.getData())
                .papel(subscricao.getAcao().getPapel())
                .valor(subscricao.getValor().doubleValue())
                .idCarteira(subscricao.getCarteira().getId())
                .idUsuario(subscricao.getUsuario().getId())
                .quantidade(subscricao.getQuantidade())
                .build();
        return SubscricaoDtoMapper
                .builder()
                .subscricaoDTO(subscricaoDTO)
                .build();
    }

    public SubscricaoDTO map() {
        return subscricaoDTO;
    }
}
