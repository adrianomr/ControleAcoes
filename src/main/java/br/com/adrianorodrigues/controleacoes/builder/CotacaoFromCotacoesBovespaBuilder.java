package br.com.adrianorodrigues.controleacoes.builder;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;

import java.text.ParseException;

public class CotacaoFromCotacoesBovespaBuilder {
    public static Cotacao build(Acao acao, CotacoesBovespaDto cotacoesBovespaDto) throws ParseException {
        return Cotacao
                .builder()
                .papel(acao.getPapel())
                .valorFechamento(cotacoesBovespaDto.getPrecoUltimoNegocio())
                .data(DateFromStringUtil.getDate(cotacoesBovespaDto.getData()))
                .build();
    }
}
