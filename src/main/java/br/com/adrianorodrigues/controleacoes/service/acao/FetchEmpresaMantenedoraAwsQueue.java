package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.aws.AwsQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class FetchEmpresaMantenedoraAwsQueue extends AwsQueueService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private AcaoRedisService acaoRedisService;
    private static final String QUEUE_NAME = "fetch-empresa-mantenedora";

    public FetchEmpresaMantenedoraAwsQueue(@Autowired AwsQueueService awsQueueService) throws JMSException {
        awsQueueService.initQueue(QUEUE_NAME);
    }

    public void sendMessage(Acao acao) {
        jmsTemplate.convertAndSend(QUEUE_NAME, acao.getPapel());
    }

    @JmsListener(destination = QUEUE_NAME)
    public void listener(String acao) {
        acaoRedisService.save(1L, acao);
        log.info("Acao:" + acao);
        log.info("Redis:" + acaoRedisService.findById(1L));
    }
}
