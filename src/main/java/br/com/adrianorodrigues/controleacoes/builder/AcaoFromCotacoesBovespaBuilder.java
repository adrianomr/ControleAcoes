package br.com.adrianorodrigues.controleacoes.builder;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;

import java.math.BigDecimal;

public class AcaoFromCotacoesBovespaBuilder {

    AcaoFromCotacoesBovespaBuilder() {
    }

    public static Acao build(CotacoesBovespaDto cotacoesBovespaDto) {
        return Acao
                .builder()
                .nomeEmpresa(cotacoesBovespaDto.getNomeResumidoEmpresaEmissora())
                .papel(cotacoesBovespaDto.getCodNegociacaoPapel())
                .quantidade(0)
                .valor(new BigDecimal(0))
                .codigoBdi(cotacoesBovespaDto.getCodigoBdi())
                .tipoMercado(cotacoesBovespaDto.getTipoMercado())
                .build();
    }

}
