package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessSalvaAcoesHistoricasSequencial {
    @Autowired
    FileUtil fileUtil;
    @Autowired
    private AcaoService acaoService;
    private List<Acao> loteAcoes = new ArrayList<>();
    private List<String> papel = new ArrayList<>();

    public int execute() {
        String folderName = "/cotacoes/txt";
        List<String> files = fileUtil.listFilesForFolder(folderName);

        for (String file : files) {
            try {
                fileUtil.readFile(folderName + "/" + file, new Callback());
            } catch (IOException e) {
                LogUtil.getLogger().error(e.getMessage());
            }
        }
        acaoService.insertListAcao(loteAcoes);
        papel = new ArrayList<>();
        loteAcoes = new ArrayList<>();
        return files.size();
    }

    private class Callback implements ICallback {

        @Override
        public void callback(Object result) {

            if (naoEhCabecalhoOuRodape(result)) {
                CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build((String) result);
                Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                if (acaoService.findAcaoByPapel(acao.getPapel()) == null && !papel.contains(acao.getPapel())) {
                    loteAcoes.add(acao);
                    papel.add(acao.getPapel());
                }
                if (loteAcoes.size() == 1000) {
                    acaoService.insertListAcao(loteAcoes);
                    loteAcoes = new ArrayList<>();
                    papel = new ArrayList<>();
                }
            }
        }

        private boolean naoEhCabecalhoOuRodape(Object result) {
            String linha = (String) result;
            return linha.trim().length() > 50;
        }
    }

}
