package br.com.adrianorodrigues.controleacoes.service.b3;

import br.com.adrianorodrigues.controleacoes.service.aws.AwsQueueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class FetchB3DataAwsQueueTest {
    @Mock
    ProcessB3Service processB3Service;
    @Mock
    JmsTemplate jmsTemplate;
    @Mock
    AwsQueueService awsQueueService;
    FetchB3DataAwsQueue fetchB3DataAwsQueue;
    AnosEmCacheSet anosEmCacheSet;
    private final static int ANO = 2020;

    @BeforeEach
    void setUp() throws JMSException {
        anosEmCacheSet = new AnosEmCacheSet();
        fetchB3DataAwsQueue = new FetchB3DataAwsQueue(jmsTemplate, processB3Service, anosEmCacheSet, awsQueueService);
    }

    @Test
    void sendMessage() {
        fetchB3DataAwsQueue.sendMessage(ANO);
        Mockito.verify(jmsTemplate).convertAndSend("fetch-b3-data", ANO);
    }

    @Test
    void listenerWhenYearNotProcessedShouldProcess() throws IOException {
        fetchB3DataAwsQueue.listener(ANO);
        Mockito.verify(processB3Service).process(ANO);
    }

    @Test()
    void listenerWhenProcessThrowsErrorShouldntAddAnoToSet() throws IOException {
        Mockito.doThrow(new IOException()).when(processB3Service).process(ANO);
        fetchB3DataAwsQueue.listener(ANO);
        Assertions.assertEquals(0, anosEmCacheSet.getAnosEmCache().size());
    }


    @Test
    void listenerWhenYearProcessedShouldntProcess() throws IOException {
        anosEmCacheSet.getAnosEmCache().add(2020);
        fetchB3DataAwsQueue.listener(ANO);
        Mockito.verify(processB3Service, Mockito.never()).process(ANO);
    }
}