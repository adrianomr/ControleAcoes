package br.com.adrianorodrigues.controleacoes.builder;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import org.junit.jupiter.api.Test;

class CotacoesBovespaDtoBuilderTest {

    @Test
    void build() {
        CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build("012020010202AALR3       010ALLIAR      ON      NM   R$  000000000182900000000019000000000001828000000000186800000000019000000000001899000000000190102443000000000000585800000000001094619600000000000000009999123100000010000000000000BRAALRACNOR6101");
        System.out.println(cotacoesBovespaDto);
    }
}