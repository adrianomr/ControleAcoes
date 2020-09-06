package br.com.adrianorodrigues.controleacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteiraDTO {
    private List<AcaoDTO> acoes;
}
