package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ChartSubgrupoDto {
    List<GrupoAcaoDto> grupoAcaoList;
    Double valorTotal;
}
