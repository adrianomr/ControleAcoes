package br.com.adrianorodrigues.ControleAcoes.scheduled;

import br.com.adrianorodrigues.ControleAcoes.dto.StatusInicializacaoSingleton;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessDownloadBovespaCotacoesHistoricasDia;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaAcoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaBovespaCotacoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessUnzipBovespaCotacoesHistoricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class AtualizaCotacaoDiaria {
    private final AtomicBoolean processed = new AtomicBoolean(false);
    @Autowired
    private ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial;
    @Autowired
    private ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial;

    //Executa função de 15 em 15 min
    @Scheduled(fixedDelay = 900000, initialDelay = 1000)
    public void atualizaCotacoesDiarias() {
        //todo: testar funcao
        if (StatusInicializacaoSingleton.getSingleton().isInicializou()) {
            processed.set(true);
            ProcessDownloadBovespaCotacoesHistoricasDia.execute();
            ProcessUnzipBovespaCotacoesHistoricas.execute();
            processSalvaAcoesHistoricasSequencial.execute();
            processSalvaBovespaCotacoesHistoricasSequencial.execute();
        }
    }

    public AtomicBoolean isProcessed() {
        return processed;
    }
}
