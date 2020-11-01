package br.com.adrianorodrigues.controleacoes.service.b3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
@Setter
public class AnosEmCacheSet {
    Set<Integer> anosEmCache;

    public AnosEmCacheSet() {
        anosEmCache = new HashSet<>();
    }
}
