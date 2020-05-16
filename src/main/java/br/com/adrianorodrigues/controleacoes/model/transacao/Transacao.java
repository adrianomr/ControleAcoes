package br.com.adrianorodrigues.controleacoes.model.transacao;


import br.com.adrianorodrigues.controleacoes.model.Acao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacao")
public class Transacao {
    private int id;
    private Acao acao;
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;
}
