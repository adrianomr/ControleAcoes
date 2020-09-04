package br.com.adrianorodrigues.controleacoes.scheduled;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AtualizaAcoesListadasTest {
    @Autowired
    AtualizaAcoesListadas atualizaAcoesListadas;
    @Autowired
    AcaoRepository acaoRepository;

    @Test
    void atualizaAcoesListadas() {
        acaoRepository.deleteAll();
        atualizaAcoesListadas.atualizaAcoesListadas();
        List<Acao> acaoList = acaoRepository.findAll();
        assertTrue(acaoList.size() > 0);
    }
}