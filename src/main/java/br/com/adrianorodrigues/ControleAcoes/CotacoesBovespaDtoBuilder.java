package br.com.adrianorodrigues.ControleAcoes;

import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;

public class CotacoesBovespaDtoBuilder {
    public static CotacoesBovespaDto build(String linhaArquivo){
        CotacoesBovespaDto cotacoesBovespaDto = new CotacoesBovespaDto();
        cotacoesBovespaDto
                .setTipoRegistro(Integer.parseInt(linhaArquivo.substring(0,2)))
                .setData(linhaArquivo.substring(2,10));
        return cotacoesBovespaDto;
    }
}
