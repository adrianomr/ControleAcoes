package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.GrupoAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.Builder;

@Builder
public class GrupoAcaoMapper {
    GrupoAcao grupoAcao;

    public static GrupoAcaoMapper from(GrupoAcaoDto grupoAcaoDto, Usuario usuario) {
        GrupoAcao grupoAcao = GrupoAcao
                .builder()
                .id(grupoAcaoDto.getId())
                .nome(grupoAcaoDto.getNome())
                .nota(grupoAcaoDto.getNota())
                .percentual(grupoAcaoDto.getPercentual())
                .usuario(usuario)
                .acaoList(grupoAcaoDto.getAcaoList())
                .subgrupoList(grupoAcaoDto.getSubgrupoList())
                .build();
        return GrupoAcaoMapper
                .builder()
                .grupoAcao(grupoAcao)
                .build();
    }

    public GrupoAcao map() {
        return grupoAcao;
    }
}
