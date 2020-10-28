package br.com.adrianorodrigues.controleacoes.scheduled;

import br.com.adrianorodrigues.controleacoes.processor.ProcessDownloadBovespaCotacoesHistoricasDia;
import br.com.adrianorodrigues.controleacoes.processor.ProcessSalvaAcoesHistoricasSequencialDia;
import br.com.adrianorodrigues.controleacoes.processor.ProcessUnzipBovespaCotacoesHistoricasDia;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AtualizaAcoesListadas {

    @Autowired
    AcaoRepository acaoRepository;
    @Autowired
    ProcessSalvaAcoesHistoricasSequencialDia processSalvaAcoesHistoricasSequencialDia;

    //Executa função de 12 em 12 horas
    //Valor em milisegundos
    @Scheduled(fixedDelay = 43200000)
    public void scheduledProcedure() {
            ProcessDownloadBovespaCotacoesHistoricasDia.execute();
            ProcessUnzipBovespaCotacoesHistoricasDia.execute();
            processSalvaAcoesHistoricasSequencialDia.execute();
    }

}
