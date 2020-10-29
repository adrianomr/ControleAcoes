package br.com.adrianorodrigues.controleacoes.service.acao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class AcaoRedisServiceTest {
    @Mock
    RedisTemplate<Long, String> redisTemplate;
    @Mock
    ListOperations<Long, String> listOperations;
    @Mock
    BoundListOperations<Long, String> boundListOperationsHasData;
    @Mock
    BoundListOperations<Long, String> boundListOperationsHasNoData;
    @InjectMocks
    AcaoRedisService acaoRedisService;
    private List<String> acoesExpected = Arrays.asList("teste");
    @BeforeEach
    void setUp() {
        Mockito.lenient().when(redisTemplate.opsForList()).thenReturn(listOperations);
        Mockito.lenient().when(redisTemplate.boundListOps(1L)).thenReturn(boundListOperationsHasData);
        Mockito.lenient().when(redisTemplate.boundListOps(2L)).thenReturn(boundListOperationsHasNoData);
        Mockito.lenient().when(boundListOperationsHasData.size()).thenReturn(1L);
        Mockito.lenient().when(boundListOperationsHasData.range(0, 1)).thenReturn(acoesExpected);
    }

    @Test
    void save() {
        acaoRedisService.save(1L, "teste");
        Mockito.verify(listOperations).leftPush(1L, "teste");
    }

    @Test
    void findByIdShouldReturnList() {
        List<String> acoes = acaoRedisService.findById(1L);
        Assertions.assertEquals(acoesExpected, acoes);
    }
    @Test
    void findByIdWhenSizeZeroShouldReturnEmptyList() {
        Mockito.when(boundListOperationsHasNoData.size()).thenReturn(0L);
        List<String> acoes = acaoRedisService.findById(2L);
        Assertions.assertEquals(new ArrayList<>(), acoes);
    }
    @Test
    void findByIdWhenSizeNullShouldReturnEmptyList() {
        Mockito.when(boundListOperationsHasNoData.size()).thenReturn(null);
        List<String> acoes = acaoRedisService.findById(2L);
        Assertions.assertEquals(new ArrayList<>(), acoes);
    }
}