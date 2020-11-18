package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoDTO {
    private Long id;
    private String papel;
    private Double valor;
    private Double precoMedio;
    private Long quantidade;
    private Double percentualRebalanceamento;
    private Double lucroPrejuizo;
}
