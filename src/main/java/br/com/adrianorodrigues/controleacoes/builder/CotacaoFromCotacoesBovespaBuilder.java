package br.com.adrianorodrigues.controleacoes.builder;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.util.DateFromStringUtil;

import java.text.ParseException;

public class CotacaoFromCotacoesBovespaBuilder {
    public static Cotacao build(Acao acao, CotacoesBovespaDto cotacoesBovespaDto) throws ParseException {
        Cotacao cotacao = new Cotacao();
        cotacao.setAcao(acao);
        cotacao.setValorFechamento(cotacoesBovespaDto.getPrecoUltimoNegocio());
        cotacao.setData(DateFromStringUtil.getDate(cotacoesBovespaDto.getData()));
        return cotacao;
    }
}
