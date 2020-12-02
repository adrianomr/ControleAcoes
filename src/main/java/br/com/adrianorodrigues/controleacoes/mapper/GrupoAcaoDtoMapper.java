package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.GrupoAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import lombok.Builder;

@Builder
public class GrupoAcaoDtoMapper {
    GrupoAcaoDto grupoAcaoDto;

    public static GrupoAcaoDtoMapper from(GrupoAcao grupoAcao) {
        GrupoAcaoDto grupoAcaoDto = GrupoAcaoDto
                .builder()
                .id(grupoAcao.getId())
                .nome(grupoAcao.getNome())
                .nota(grupoAcao.getNota())
                .percentual(grupoAcao.getPercentual())
                .acaoList(grupoAcao.getAcaoList())
                .subgrupoList(grupoAcao.getSubgrupoList())
                .valorInvestido(0D)
                .build();
        return GrupoAcaoDtoMapper
                .builder()
                .grupoAcaoDto(grupoAcaoDto)
                .build();
    }

    public GrupoAcaoDto map() {
        return grupoAcaoDto;
    }
}
