package br.com.adrianorodrigues.controleacoes.service.acao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AcaoRedisService {
    @Autowired
    private RedisTemplate<Long, String> redisTemplate;

    public void save(Long id, String acao) {
        redisTemplate.opsForList().leftPush(id, acao);
    }

    public List<String> findById(Long id) {
        Long size = redisTemplate.boundListOps(id).size();
        if (size != null)
            return redisTemplate.boundListOps(id).range(0, size);
        return new ArrayList<>();
    }
}
