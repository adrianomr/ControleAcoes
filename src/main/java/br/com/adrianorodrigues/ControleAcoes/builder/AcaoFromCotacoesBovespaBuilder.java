package br.com.adrianorodrigues.ControleAcoes.builder;

import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;

import java.math.BigDecimal;

public class AcaoFromCotacoesBovespaBuilder {

    public static Acao build(CotacoesBovespaDto cotacoesBovespaDto) {
        Acao acao = new Acao();
        acao.setNomeEmpresa(cotacoesBovespaDto.getNomeResumidoEmpresaEmissora());
        acao.setPapel(cotacoesBovespaDto.getCodNegociacaoPapel());
        acao.setQuantidade(0);
        acao.setValor(new BigDecimal(0));
        return acao;
    }

}
