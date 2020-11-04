package br.com.adrianorodrigues.controleacoes.service.b3;

import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.controleacoes.client.CotacaoCliente;
import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.interfaces.ICallback;
import br.com.adrianorodrigues.controleacoes.service.acao.CotacaoBovespaRedisService;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class ProcessB3Service {
    @Autowired
    private CotacaoBovespaRedisService cotacaoBovespaRedisService;

    public void process(int ano) throws IOException {
        log.info("Processando ano:" + ano);
        String folderName = "/cotacoes/txt";
        CotacaoCliente.getCotacoes(ano);
        FileUtil fileUtil = new FileUtil();
        fileUtil.deleteFiles(folderName);
        ZipUtil.Unzip(fileUtil.getFolderCreateIfNotExists("/cotacoes/zip").getPath() + "/COTAHIST_A" + ano + ".ZIP", fileUtil.getFolderCreateIfNotExists(folderName).getPath());
        List<String> files = fileUtil.listFilesForFolder(folderName);
        for (String file : files) {
            fileUtil.readFile(folderName + "/" + file, new Callback(ano));
        }
    }

    private class Callback implements ICallback {

        int ano;

        public Callback(int ano) {
            this.ano = ano;
        }

        @Override
        public void callback(Object result) {
            if (naoEhCabecalhoOuRodape(result)) {
                CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build((String) result);
                cotacaoBovespaRedisService.save(ano, cotacoesBovespaDto);
                log.info(cotacoesBovespaDto.getCodigoPapel());
            }
        }

        private boolean naoEhCabecalhoOuRodape(Object result) {
            String linha = (String) result;
            return linha.trim().length() > 50;
        }
    }
}
