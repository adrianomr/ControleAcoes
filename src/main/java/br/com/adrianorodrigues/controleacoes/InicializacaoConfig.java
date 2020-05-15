package br.com.adrianorodrigues.controleacoes;

import br.com.adrianorodrigues.controleacoes.dto.StatusInicializacaoSingleton;
import br.com.adrianorodrigues.controleacoes.processor.ProcessDownloadBovespaCotacoesHistoricas;
import br.com.adrianorodrigues.controleacoes.processor.ProcessSalvaAcoesHistoricasSequencial;
import br.com.adrianorodrigues.controleacoes.processor.ProcessSalvaBovespaCotacoesHistoricasSequencial;
import br.com.adrianorodrigues.controleacoes.processor.ProcessUnzipBovespaCotacoesHistoricas;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InicializacaoConfig {
    @Bean
    @Profile("!test")
    public CommandLineRunner run(ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial,
                                 ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial,
                                 AcaoRepository acaoRepository) {
        return args -> {
            if (acaoRepository.count() <= 0) {
                ProcessDownloadBovespaCotacoesHistoricas.execute();
                ProcessUnzipBovespaCotacoesHistoricas.execute();
                processSalvaAcoesHistoricasSequencial.execute();
                processSalvaBovespaCotacoesHistoricasSequencial.execute();
                StatusInicializacaoSingleton.getSingleton().setInicializou();
            }
        };
    }
}