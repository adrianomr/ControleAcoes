package br.com.adrianorodrigues.controleacoes.conf;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public RedisTemplate<Integer, CotacoesBovespaDto> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Integer, CotacoesBovespaDto> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
