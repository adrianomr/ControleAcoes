package br.com.adrianorodrigues.controleacoes.model.transacao;


import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @NotNull
    private Acao acao;
    @Column
    @NotNull
    private TipoTransacao tipoTransacao;
    @NotNull
    @Column
    private LocalDateTime data;
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
}
