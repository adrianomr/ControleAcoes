package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles("test")
class EmpresaMantenedoraRepositoryTest {
    @Autowired EmpresaMantenedoraRepository empresaMantenedoraRepository;
    @Autowired AcaoRepository acaoRepository;
    @Test
    void findByCodigoBdiAndTipoMercado() {
        Acao acao = acaoRepository.findOneByPapel("BCFF11");
        Set<String> empresaMantenedora = empresaMantenedoraRepository.findAllByAcao(acao).stream().map((empresa)-> empresa.getDescricao()).collect(Collectors.toSet());
        MatcherAssert.assertThat(empresaMantenedora, CoreMatchers.hasItems("Empresa teste"));
    }
}