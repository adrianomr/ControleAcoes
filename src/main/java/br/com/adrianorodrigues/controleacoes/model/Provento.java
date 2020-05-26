package br.com.adrianorodrigues.controleacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date dataPagamento;
    @Column
    private BigDecimal valor;
}
