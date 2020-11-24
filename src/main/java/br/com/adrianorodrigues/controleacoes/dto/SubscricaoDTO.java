package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class SubscricaoDTO {
    private Long id;
    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idCorretora;
    @NotNull
    private LocalDate data;
    @NotNull
    private String papel;
    @NotNull
    private Long quantidade;
    @NotNull
    private Double valor;
}
