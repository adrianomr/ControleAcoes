package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.CorretoraDTO;
import br.com.adrianorodrigues.controleacoes.model.Corretora;
import lombok.Builder;

@Builder
public class CorretoraMapper {
    Corretora corretora;

    public static CorretoraMapper from(CorretoraDTO corretoraDTO) {
        Corretora corretora = Corretora
                .builder()
                .id(corretoraDTO.getId())
                .nome(corretoraDTO.getNome())
                .build();
        return CorretoraMapper
                .builder()
                .corretora(corretora)
                .build();
    }

    public Corretora map() {
        return corretora;
    }
}
