package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles("test")
class AcaoRepositoryTest {
    @Autowired
    AcaoRepository acaoRepository;

    @Test
    void findByCodigoBdiAndTipoMercado() {
        List acaoList = acaoRepository.findByCodigoBdiInAndTipoMercado(Arrays.asList("02", "12"), 10);
        Assertions.assertNotEquals(null, acaoList);
    }
}