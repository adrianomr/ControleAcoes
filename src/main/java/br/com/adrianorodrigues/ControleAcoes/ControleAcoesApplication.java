package br.com.adrianorodrigues.ControleAcoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleAcoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleAcoesApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run() throws Exception {
//		return args -> {
//			CotacaoCliente.getCotacoes(2020);
//		};
//	}

}
