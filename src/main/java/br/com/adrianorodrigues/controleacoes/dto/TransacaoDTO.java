package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Data;

@Data
public class TransacaoDTO {
    private Long idUsuario;
    private String papel;
    private Double valor;
}
