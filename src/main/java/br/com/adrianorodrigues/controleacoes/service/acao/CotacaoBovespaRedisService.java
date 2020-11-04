package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CotacaoBovespaRedisService {
    @Autowired
    private RedisTemplate<Integer, CotacoesBovespaDto> redisTemplate;

    public void save(int id, CotacoesBovespaDto cotacoesBovespaDto) {
        redisTemplate.opsForList().leftPush(id, cotacoesBovespaDto);
    }

    public List<CotacoesBovespaDto> findById(int id) {
        Long size = redisTemplate.boundListOps(id).size();
        if (size != null)
            return redisTemplate.boundListOps(id).range(0, size);
        return new ArrayList<>();
    }


    public List<CotacoesBovespaDto> findByIdPaginated(int id, Long pageSize,Long page) {
        Long begin = (page - 1) * pageSize;
        Long end = page * pageSize;
        return redisTemplate.boundListOps(id).range(begin, end);
    }

    public Long getSize(int id){
        return redisTemplate.boundListOps(id).size();
    }
}
