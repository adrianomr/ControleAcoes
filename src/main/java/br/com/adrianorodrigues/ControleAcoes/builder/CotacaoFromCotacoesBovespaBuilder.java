package br.com.adrianorodrigues.ControleAcoes.builder;

import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import br.com.adrianorodrigues.ControleAcoes.util.DateFromStringUtil;

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
