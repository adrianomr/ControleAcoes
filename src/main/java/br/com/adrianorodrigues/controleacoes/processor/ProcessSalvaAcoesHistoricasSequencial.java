package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.dto.HashMapAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.AcaoService;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessSalvaAcoesHistoricasSequencial {
    @Autowired
    private AcaoService acaoService;

    public int execute() {
        List<String> files = FileUtil.listFilesForFolder(new File("src/main/resources/cotacoes/txt"));
        ArrayList<Acao> loteAcoes = new ArrayList<Acao>();
        for (int i = 0; i < files.size(); i++) {
            System.out.println("Running task " + i);
            try {
                String data = FileUtil.readFile("src/main/resources/cotacoes/txt/" + files.get(i));
                String[] cotacoes = data.split("\n");
                for (int x = 1; x < cotacoes.length - 1; x++) {
                    CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build(cotacoes[x]);
                    Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                    if (!HashMapAcaoDto.getHashAcaoDto().containsKey(acao.getPapel())) {
                        HashMapAcaoDto.getHashAcaoDto().put(acao.getPapel(), acao);
                        loteAcoes.add(acao);
                        if (loteAcoes.size() == 1000) {
                            acaoService.insertListAcao(loteAcoes);
                            loteAcoes = new ArrayList<Acao>();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Finished task " + i);
        }
        acaoService.insertListAcao(loteAcoes);
        loteAcoes = null;
        System.out.println("HASHMAPSIZE: " + HashMapAcaoDto.getHashAcaoDto().size());
        return files.size();
    }
}
