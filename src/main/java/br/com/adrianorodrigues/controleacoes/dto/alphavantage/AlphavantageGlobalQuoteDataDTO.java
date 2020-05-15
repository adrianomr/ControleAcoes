package br.com.adrianorodrigues.controleacoes.dto.alphavantage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlphavantageGlobalQuoteDataDTO {
    @JsonProperty("01. symbol")
    String papel;

    @JsonProperty("02. open")
    Double abertura;

    @JsonProperty("03. high")
    Double maiorPreco;

    @JsonProperty("04. low")
    Double menorPreco;

    @JsonProperty("05. price")
    Double precoAtual;

    @JsonProperty("06. volume")
    Double volume;

    @JsonProperty("07. latest trading day")
    Date data;

    @JsonProperty("08. previous close")
    Double preFechamento;

    @JsonProperty("09. change")
    Double variacao;

    @JsonProperty("10. change percent")
    String variacaoPercentual;
}
