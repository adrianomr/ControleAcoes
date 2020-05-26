package br.com.adrianorodrigues.controleacoes.model.transacao;


import br.com.adrianorodrigues.controleacoes.model.Acao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "acao_id")
    private Acao acao;
    @Column
    private TipoTransacao tipoTransacao;
    @Column
    private BigDecimal valor;
}
