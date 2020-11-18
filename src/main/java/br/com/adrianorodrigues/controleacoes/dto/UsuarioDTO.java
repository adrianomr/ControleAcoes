package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String username;
}
