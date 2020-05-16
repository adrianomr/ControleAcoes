package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.dto.ArrayListCotacaoDto;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.dto.HashMapAcaoDto;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.service.AcaoService;
import br.com.adrianorodrigues.controleacoes.service.CotacaoService;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String FOLDER_NAME = "/cotacoes/txt";
        List<String> files = FileUtil.listFilesForFolder(FOLDER_NAME);
        for (int i = 0; i < files.size(); i++) {
            System.out.println("Running task " + i);
            try {
                String data = FileUtil.readFile(FOLDER_NAME + files.get(i));
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
