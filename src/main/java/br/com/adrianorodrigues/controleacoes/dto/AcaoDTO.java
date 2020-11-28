package br.com.adrianorodrigues.controleacoes.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AcaoDTO {
    private Long id;
    private String papel;
    private Double valor;
    private Double precoMedio;
    private Long quantidade;
    private Double percentualRebalanceamento;
    private Double nota;
    private Double lucroPrejuizo;
}
