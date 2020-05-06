package br.com.adrianorodrigues.ControleAcoes;

import br.com.adrianorodrigues.ControleAcoes.dto.StatusInicializacaoSingleton;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessDownloadBovespaCotacoesHistoricas;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaAcoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaBovespaCotacoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessUnzipBovespaCotacoesHistoricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class inicializacaoConfig {
    @Autowired
    private ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial;
    @Autowired
    private ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial;

    @Bean
    @Profile("!test")
    public CommandLineRunner run() throws Exception {
        return args -> {
            ProcessDownloadBovespaCotacoesHistoricas.execute();
            ProcessUnzipBovespaCotacoesHistoricas.execute();
            processSalvaAcoesHistoricasSequencial.execute();
            processSalvaBovespaCotacoesHistoricasSequencial.execute();
            StatusInicializacaoSingleton.getSingleton().setInicializou();
        };
    }
}