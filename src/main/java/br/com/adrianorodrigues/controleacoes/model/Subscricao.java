package br.com.adrianorodrigues.controleacoes.model;


import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscricao")
public class Subscricao {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "acao_id")
    @NotNull
    private Acao acao;
    @NotNull
    @Column
    private LocalDate data;
    @NotNull
    @Column
    private long quantidade;
    @NotNull
    @Column
    private BigDecimal valor;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "transacao_id")
    private Transacao transacao;
}
