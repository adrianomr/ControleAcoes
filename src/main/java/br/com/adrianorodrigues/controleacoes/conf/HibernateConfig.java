package br.com.adrianorodrigues.controleacoes.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hibernate-${persistenceTarget:local}.properties")
@Profile("!test")
public class HibernateConfig {
}
