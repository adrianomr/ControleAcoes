package br.com.adrianorodrigues.controleacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "provento")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Provento {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;
    @Column
    private LocalDate dataPagamento;
    @Column
    private BigDecimal valor;
}
