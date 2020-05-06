package br.com.adrianorodrigues.ControleAcoes.scheduled;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.com.adrianorodrigues.ControleAcoes.scheduled")
public class ScheduledConfig {
}