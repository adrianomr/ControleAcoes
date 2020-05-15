package br.com.adrianorodrigues.controleacoes.builder;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class CotacaoFromCotacoesBovespaBuilderTest {

    @Test
    void build() throws ParseException {
        CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build("012020010202AALR3       010ALLIAR      ON      NM   R$  000000000182900000000019000000000001828000000000186800000000019000000000001899000000000190102443000000000000585800000000001094619600000000000000009999123100000010000000000000BRAALRACNOR6101");
        System.out.println(cotacoesBovespaDto);
        Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
        System.out.println(acao);
        Cotacao cotacao = CotacaoFromCotacoesBovespaBuilder.build(acao, cotacoesBovespaDto);
        System.out.println(cotacao);
    }
}