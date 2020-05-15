package br.com.adrianorodrigues.controleacoes.dto.alphavantage;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlphavantageGlobalQuoteDTO {
    @JsonProperty("Global Quote")
    AlphavantageGlobalQuoteDataDTO cotacao;

    public Cotacao toCotacao(Acao acao) {
        return Cotacao
                .builder()
                .acao(acao)
                .data(cotacao.getData())
                .valorFechamento(cotacao.getPrecoAtual())
                .build();
    }
}
