package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class FetchEmpresaMantenedoraAwsQueue {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String QUEUE_NAME = "fetch-empresa-mantenedora";

    public FetchEmpresaMantenedoraAwsQueue(@Autowired SQSConnectionFactory sqsConnectionFactory) throws JMSException {
        AmazonSQSMessagingClientWrapper client = sqsConnectionFactory.createConnection().getWrappedAmazonSQSClient();
        if (!client.queueExists(QUEUE_NAME)) {
            client.createQueue(QUEUE_NAME);
        }
    }

    public void sendMessage(Acao acao) {
        jmsTemplate.convertAndSend(QUEUE_NAME, acao.getPapel());
    }
    @Autowired
    private RedisTemplate<Long, String> redisTemplate;

    public void save(Long id, String book) {
        redisTemplate.opsForValue().set(id, book);
    }

    public String findById(Long id) {
        return redisTemplate.opsForValue().get(id);
    }
    @JmsListener(destination = QUEUE_NAME)
    public void listener(String acao) {
        save(1L, acao);
        log.info("Acao:" + acao);
        log.info("Redis:" + findById(1L));
    }
}
