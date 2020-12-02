package br.com.adrianorodrigues.controleacoes.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotacao")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cotacao {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date data;

    @Column(columnDefinition = "numeric(20,4)")
    private Double valorFechamento;

    @Column(columnDefinition = "varchar(100)")
    private String papel;

    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;

}
