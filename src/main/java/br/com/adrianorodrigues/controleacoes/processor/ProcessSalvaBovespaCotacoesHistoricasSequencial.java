package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.dto.ArrayListCotacaoDto;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.service.AcaoService;
import br.com.adrianorodrigues.controleacoes.service.CotacaoService;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessSalvaBovespaCotacoesHistoricasSequencial {
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private CotacaoService cotacaoService;
    @Autowired
    FileUtil fileUtil;
    private ArrayList<Cotacao> cotacaoArrayList = new ArrayList<>();
    private boolean primeiraLinha = true;

    public int execute() {
        String folderName = "/cotacoes/txt";
        List<String> files = fileUtil.listFilesForFolder(folderName);
        for (int i = 0; i < files.size(); i++) {
            try {
                fileUtil.readFile(folderName + "/" + files.get(i), new Callback());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cotacaoService.insertListCotacao(cotacaoArrayList);
        cotacaoArrayList = null;
        return files.size();
    }

    private class Callback implements ICallback {

        @Override
        public void callback(Object result) {
            try {
                if (primeiraLinha == false) {
                    CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build((String) result);
                    Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                    Cotacao cotacao = CotacaoFromCotacoesBovespaBuilder.build(acao, cotacoesBovespaDto);
                    cotacaoArrayList.add(cotacao);
                    if (ArrayListCotacaoDto.getArrayListCotacaoDto().size() == 1000) {
                        cotacaoService.insertListCotacao(cotacaoArrayList);
                        cotacaoArrayList = new ArrayList<>();
                    }
                } else
                    primeiraLinha = false;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
