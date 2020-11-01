package br.com.adrianorodrigues.controleacoes.service.b3;

import br.com.adrianorodrigues.controleacoes.service.aws.AwsQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.IOException;

@Component
@Slf4j
public class FetchB3DataAwsQueue extends AwsQueueService {
    private JmsTemplate jmsTemplate;
    private ProcessB3Service processB3Service;
    AnosEmCacheSet anosEmCacheSet;
    private static final String QUEUE_NAME = "fetch-b3-data";

    public FetchB3DataAwsQueue(
            @Autowired JmsTemplate jmsTemplate,
            @Autowired ProcessB3Service processB3Service,
            @Autowired AnosEmCacheSet anosEmCacheSet,
            @Autowired AwsQueueService awsQueueService) throws JMSException {
        this.jmsTemplate = jmsTemplate;
        this.processB3Service = processB3Service;
        this.anosEmCacheSet = anosEmCacheSet;
        awsQueueService.initQueue(QUEUE_NAME);
    }

    public void sendMessage(int ano) {
        jmsTemplate.convertAndSend(QUEUE_NAME, ano);
    }

    @JmsListener(destination = QUEUE_NAME)
    public void listener(int ano) {
        try {
            log.info("Message received. Ano: " + ano);
            if (!anosEmCacheSet.getAnosEmCache().contains(ano)) {
                processB3Service.process(ano);
                anosEmCacheSet.getAnosEmCache().add(ano);
            }
        } catch (IOException e) {
            log.error("Nao foi possivel encontrar o arquivo", e);
        }
    }
}
