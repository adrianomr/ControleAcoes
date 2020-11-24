package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RebalanceamentoAcaoDto {

    private Long id;
    private String papel;
    private Long idUsuario;
    private Double percentual;
    private Double nota;

}
