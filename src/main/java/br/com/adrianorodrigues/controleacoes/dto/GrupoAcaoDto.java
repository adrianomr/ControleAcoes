package br.com.adrianorodrigues.controleacoes.dto;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class GrupoAcaoDto {
    List<Acao> acaoList;
    List<GrupoAcao> subgrupoList;
    private Long id;
    private String nome;
    private Double percentual;
    private Double nota;
    private Long idUsuario;
}
