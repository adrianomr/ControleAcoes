package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.CotacaoAtualDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CotacaoAtualService {
    public CotacaoAtualDTO getCotacaoAtual(String acao) throws IOException {
        acao = acao.endsWith("F") ? acao.substring(0, acao.length() - 1) : acao;
        Document doc = Jsoup.connect("https://finance.yahoo.com/quote/" + acao + ".SA/?p=" + acao + ".SA").get();
        Elements quoteHeader = doc.select("#quote-header-info");
        List<String> strings = new ArrayList<>();
        quoteHeader.first().getAllElements().forEach(element -> strings.add(element.text()));
        Double cotacao = Double.parseDouble(strings.get(28));
        CotacaoAtualDTO cotacaoAtualDTO = new CotacaoAtualDTO();
        cotacaoAtualDTO.setPapel(acao);
        cotacaoAtualDTO.setCotacao(cotacao);
        return cotacaoAtualDTO;
    }
}
