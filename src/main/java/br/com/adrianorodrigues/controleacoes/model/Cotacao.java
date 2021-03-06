package br.com.adrianorodrigues.controleacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotacao")
@Data
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
