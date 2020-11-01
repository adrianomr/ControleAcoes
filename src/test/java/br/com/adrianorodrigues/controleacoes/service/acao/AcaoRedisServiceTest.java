package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
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
    RedisTemplate<Integer, CotacoesBovespaDto> redisTemplate;
    @Mock
    ListOperations<Integer, CotacoesBovespaDto> listOperations;
    @Mock
    BoundListOperations<Integer, CotacoesBovespaDto> boundListOperationsHasData;
    @Mock
    BoundListOperations<Integer, CotacoesBovespaDto> boundListOperationsHasNoData;
    @InjectMocks
    AcaoRedisService acaoRedisService;
    private List<CotacoesBovespaDto> acoesExpected;
    CotacoesBovespaDto cotacoesBovespaDto;
    @BeforeEach
    void setUp() {
        cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build("012020010202AALR3       010ALLIAR      ON      NM   R$  000000000182900000000019000000000001828000000000186800000000019000000000001899000000000190102443000000000000585800000000001094619600000000000000009999123100000010000000000000BRAALRACNOR6101");
        acoesExpected = Arrays.asList(cotacoesBovespaDto);
        Mockito.lenient().when(redisTemplate.opsForList()).thenReturn(listOperations);
        Mockito.lenient().when(redisTemplate.boundListOps(1)).thenReturn(boundListOperationsHasData);
        Mockito.lenient().when(redisTemplate.boundListOps(2)).thenReturn(boundListOperationsHasNoData);
        Mockito.lenient().when(boundListOperationsHasData.size()).thenReturn(1L);
        Mockito.lenient().when(boundListOperationsHasData.range(0, 1)).thenReturn(acoesExpected);
    }

    @Test
    void save() {
        acaoRedisService.save(1, cotacoesBovespaDto);
        Mockito.verify(listOperations).leftPush(1, cotacoesBovespaDto);
    }

    @Test
    void findByIdShouldReturnList() {
        List<CotacoesBovespaDto> acoes = acaoRedisService.findById(1);
        Assertions.assertEquals(acoesExpected, acoes);
    }
    @Test
    void findByIdWhenSizeZeroShouldReturnEmptyList() {
        Mockito.when(boundListOperationsHasNoData.size()).thenReturn(0L);
        List<CotacoesBovespaDto> acoes = acaoRedisService.findById(2);
        Assertions.assertEquals(new ArrayList<>(), acoes);
    }
    @Test
    void findByIdWhenSizeNullShouldReturnEmptyList() {
        Mockito.when(boundListOperationsHasNoData.size()).thenReturn(null);
        List<CotacoesBovespaDto> acoes = acaoRedisService.findById(2);
        Assertions.assertEquals(new ArrayList<>(), acoes);
    }
}