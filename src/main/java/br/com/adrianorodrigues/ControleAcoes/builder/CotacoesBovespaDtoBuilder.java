package br.com.adrianorodrigues.ControleAcoes.builder;

import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;

public class CotacoesBovespaDtoBuilder {
    public static CotacoesBovespaDto build(String linhaArquivo){
        CotacoesBovespaDto cotacoesBovespaDto = new CotacoesBovespaDto();
        cotacoesBovespaDto
                .setTipoRegistro(Integer.parseInt(linhaArquivo.substring(0, 2)))
                .setData(linhaArquivo.substring(8, 10).trim() + "/" + linhaArquivo.substring(6, 8).trim() + "/" + linhaArquivo.substring(2, 6).trim())
                .setCodigoBdi(linhaArquivo.substring(10, 12).trim())
                .setCodNegociacaoPapel(linhaArquivo.substring(12, 24).trim())
                .setTipoMercado(Integer.parseInt(linhaArquivo.substring(24, 27).trim()))
                .setNomeResumidoEmpresaEmissora(linhaArquivo.substring(27, 39).trim())
                .setEspecificacaoPapel(linhaArquivo.substring(39, 49).trim())
                .setPrazoMercadoTermo(linhaArquivo.substring(49, 52).trim())
                .setMoedaReferencia(linhaArquivo.substring(52, 56).trim())
                .setPrecoAbertura(parseDouble(linhaArquivo.substring(56, 69)))
                .setPrecoMaximo(parseDouble(linhaArquivo.substring(69, 82)))
                .setPrecoMinimo(parseDouble(linhaArquivo.substring(82, 95)))
                .setPrecoMedio(parseDouble(linhaArquivo.substring(95, 108)))
                .setPrecoUltimoNegocio(parseDouble(linhaArquivo.substring(108, 121)))
                .setPrecoMelhorOfertaCompra(parseDouble(linhaArquivo.substring(121, 134)))
                .setPrecoMelhorOfertaVenda(parseDouble(linhaArquivo.substring(134, 147)))
                .setNumeroNegociosEfetuados(Integer.parseInt(linhaArquivo.substring(147, 152)))
                .setQuantidadeTitulosNegociados(Long.parseLong(linhaArquivo.substring(152, 170)))
                .setVolumeTotalTitulos(parseDouble(linhaArquivo.substring(170, 188)))
                .setCodigoPapel(linhaArquivo.substring(242, 245).trim());
        return cotacoesBovespaDto;
    }
    private static double parseDouble(String valor){
        int posicaoVirgula = valor.length() - 2;
        return Double.parseDouble(valor.substring(0, posicaoVirgula) + "." + valor.substring(posicaoVirgula));
    }
}
