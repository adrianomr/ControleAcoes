package br.com.adrianorodrigues.controleacoes.service.aws;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class AwsQueueService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private SQSConnectionFactory sqsConnectionFactory;

    public void initQueue(String queueName) throws JMSException {
        AmazonSQSMessagingClientWrapper client = sqsConnectionFactory.createConnection().getWrappedAmazonSQSClient();
        if (!client.queueExists(queueName)) {
            client.createQueue(queueName);
        }
    }

}
