package br.com.adrianorodrigues.ControleAcoes;

import br.com.adrianorodrigues.ControleAcoes.client.CotacaoCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ControleAcoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleAcoesApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			CotacaoCliente.getCotacoes();
		};
	}

}
