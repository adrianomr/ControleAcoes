package br.com.adrianorodrigues.ControleAcoes.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hibernate-${persistenceTarget:local}.properties")
public class HibernateConfig {
}
