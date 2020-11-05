package br.com.adrianorodrigues.controleacoes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class EmpresaMantenedoraDTO {
    Long id;
    String descricao;
    Long cnpj;
    Long idAcao;
}
