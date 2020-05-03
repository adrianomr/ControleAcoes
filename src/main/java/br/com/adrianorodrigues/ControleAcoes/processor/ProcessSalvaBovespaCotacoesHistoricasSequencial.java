package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.ControleAcoes.dto.ArrayListCotacaoDto;
import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.ControleAcoes.dto.HashMapAcaoDto;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import br.com.adrianorodrigues.ControleAcoes.service.AcaoService;
import br.com.adrianorodrigues.ControleAcoes.service.CotacaoService;
import br.com.adrianorodrigues.ControleAcoes.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class ProcessSalvaBovespaCotacoesHistoricasSequencial {
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private CotacaoService cotacaoService;

    public int execute() {
        List<String> files = FileUtil.listFilesForFolder(new File("src/main/resources/cotacoes/txt"));
        for (int i = 0; i < files.size(); i++) {
            System.out.println("Running task " + i);
            try {
                String data = FileUtil.readFile("src/main/resources/cotacoes/txt/" + files.get(i));
                String[] cotacoes = data.split("\n");
                for (int x = 1; x < cotacoes.length - 1; x++) {
                    CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build(cotacoes[x]);
                    Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                    Cotacao cotacao = CotacaoFromCotacoesBovespaBuilder.build(HashMapAcaoDto.getHashAcaoDto().get(acao.getPapel()), cotacoesBovespaDto);
                    if (cotacao != null && acao != null) {
                        ArrayListCotacaoDto.getArrayListCotacaoDto().add(cotacao);
                        if (ArrayListCotacaoDto.getArrayListCotacaoDto().size() == 1000) {
                            cotacaoService.insertListCotacao(ArrayListCotacaoDto.getArrayListCotacaoDto());
                        }
                    }

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Finished task " + i);
        }
        System.out.println("HASHMAPSIZE: " + HashMapAcaoDto.getHashAcaoDto().size());
        cotacaoService.insertListCotacao(ArrayListCotacaoDto.getArrayListCotacaoDto());
        HashMapAcaoDto.limpaLista();
        return files.size();
    }
}
