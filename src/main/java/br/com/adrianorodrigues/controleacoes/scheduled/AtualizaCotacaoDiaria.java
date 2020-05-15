package br.com.adrianorodrigues.controleacoes.scheduled;

import br.com.adrianorodrigues.controleacoes.client.AlphavantageClient;
import br.com.adrianorodrigues.controleacoes.dto.StatusInicializacaoSingleton;
import br.com.adrianorodrigues.controleacoes.dto.alphavantage.AlphavantageGlobalQuoteDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Component
public class AtualizaCotacaoDiaria {
    private final AtomicBoolean processed = new AtomicBoolean(false);

    @Autowired
    AcaoRepository acaoRepository;
    @Autowired
    CotacaoRepository cotacaoRepository;
    @Autowired
    AlphavantageClient alphavantageClient;

    //Executa função de 15 em 15 min
    @Scheduled(fixedDelay = 900000, initialDelay = 1000)
    public void atualizaCotacoesDiarias() {

        if (StatusInicializacaoSingleton.getSingleton().isInicializou()) {
            List<Cotacao> cotacaoList = new ArrayList<>();
            List<Acao> acaoList = acaoRepository.findByCodigoBdiInAndTipoMercado(Arrays.asList("02", "12"), 10);
            for (Acao acao : acaoList) {
                try {
                    AlphavantageGlobalQuoteDTO alphavantageGlobalQuoteDTO = alphavantageClient.getCotacao(acao.getPapel());
                    cotacaoList.add(alphavantageGlobalQuoteDTO.toCotacao(acao));
                } catch (Exception e) {
                    Logger.getGlobal().warning("Não foi processar a acao: " + acao);
                }
            }
            cotacaoRepository.saveAll(cotacaoList);
            processed.set(true);
        }
    }

    public AtomicBoolean isProcessed() {
        return processed;
    }
}
