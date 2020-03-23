package br.com.adrianorodrigues.ControleAcoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleAcoesApplication {

	private static final Logger logger = LoggerFactory.getLogger(ControleAcoesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ControleAcoesApplication.class, args);
	}

}
