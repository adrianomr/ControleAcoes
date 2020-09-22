package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TransacaoDTO {
    private Long id;
    @NotNull
    private Long idUsuario;
    @NotNull
    private LocalDateTime data;
    @NotNull
    private String papel;
    @NotNull
    @Min(1)
    private long quantidade;
    @NotNull
    private Double valor;
}
