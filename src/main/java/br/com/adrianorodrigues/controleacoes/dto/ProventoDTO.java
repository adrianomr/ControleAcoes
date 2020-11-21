package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
public class ProventoDTO {
    Long id;
    String papel;
    LocalDate data;
    LocalDate dataPosicao;
    Double valor;
}
