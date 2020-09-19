package br.com.adrianorodrigues.controleacoes.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("test")
@PropertySource("classpath:hibernate-teste.properties")
public class HibernateTestConfig {
}
