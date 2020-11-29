package br.com.adrianorodrigues.controleacoes.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grupo_acao")
public class GrupoAcao {
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    Usuario usuario;
    @ManyToMany
    @JoinTable(
            name = "grupo_acao_acao",
            joinColumns = @JoinColumn(name = "grupo_acao_id"),
            inverseJoinColumns = @JoinColumn(name = "acao_id"))
    List<Acao> acaoList;
    @ManyToMany
    @JoinTable(
            name = "grupo_acao_grupo_acao",
            joinColumns = @JoinColumn(name = "grupo_acao_id"),
            inverseJoinColumns = @JoinColumn(name = "subgrupo_id"))
    List<GrupoAcao> subgrupoList;
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
    @Column
    private Double percentual;
    @Column
    private Double nota;
}
