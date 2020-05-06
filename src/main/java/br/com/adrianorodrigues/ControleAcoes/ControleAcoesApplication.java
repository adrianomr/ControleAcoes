package br.com.adrianorodrigues.ControleAcoes;

import br.com.adrianorodrigues.ControleAcoes.dto.StatusInicializacaoSingleton;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessDownloadBovespaCotacoesHistoricas;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaAcoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessSalvaBovespaCotacoesHistoricasSequencial;
import br.com.adrianorodrigues.ControleAcoes.processor.ProcessUnzipBovespaCotacoesHistoricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ControleAcoesApplication {
	@Autowired
	private ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial;
	@Autowired
	private ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial;

	public static void main(String[] args) {
		SpringApplication.run(ControleAcoesApplication.class, args);
	}

	@Bean
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
