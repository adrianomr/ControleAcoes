package br.com.adrianorodrigues.controleacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "corretora")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Corretora {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
}
