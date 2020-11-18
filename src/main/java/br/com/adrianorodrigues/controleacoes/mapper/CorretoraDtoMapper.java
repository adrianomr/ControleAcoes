package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.CorretoraDTO;
import br.com.adrianorodrigues.controleacoes.model.Corretora;
import lombok.Builder;

@Builder
public class CorretoraDtoMapper {
    CorretoraDTO corretoraDTO;

    public static CorretoraDtoMapper from(Corretora corretora) {
        CorretoraDTO corretoraDTO = CorretoraDTO
                .builder()
                .id(corretora.getId())
                .nome(corretora.getNome())
                .build();
        return CorretoraDtoMapper
                .builder()
                .corretoraDTO(corretoraDTO)
                .build();
    }

    public CorretoraDTO map() {
        return corretoraDTO;
    }
}
