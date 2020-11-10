package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
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
