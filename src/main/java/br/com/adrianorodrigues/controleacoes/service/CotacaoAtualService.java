package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.CotacaoAtualDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CotacaoAtualService {
    public CotacaoAtualDTO getCotacaoAtual(String acao) throws IOException {
        acao = acao.endsWith("F") ? acao.substring(0, acao.length() - 1) : acao;
        String url = "https://finance.yahoo.com/quote/" + acao + ".SA/?p=" + acao + ".SA";
        Document doc = Jsoup.connect(url).get();
        Elements quoteHeader = doc.select("#quote-header-info");
        List<String> strings = new ArrayList<>();
        try {
            quoteHeader.first().getAllElements().forEach(element -> strings.add(element.text()));
        }catch (NullPointerException e){
            log.info("Cotacao nao encontrada para {} na url {}", acao, url);
        }
        Double cotacao = strings.size() > 28 ? Double.parseDouble(strings.get(28)): 0;
        log.info(acao);
        log.info(cotacao.toString());
        CotacaoAtualDTO cotacaoAtualDTO = new CotacaoAtualDTO();
        cotacaoAtualDTO.setPapel(acao);
        cotacaoAtualDTO.setCotacao(cotacao);
        return cotacaoAtualDTO;
    }
}
