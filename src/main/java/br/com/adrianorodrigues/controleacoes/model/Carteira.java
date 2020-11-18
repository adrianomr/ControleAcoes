package br.com.adrianorodrigues.controleacoes.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carteira")
public class Carteira {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "corretora_id")
    private Corretora corretora;
}
