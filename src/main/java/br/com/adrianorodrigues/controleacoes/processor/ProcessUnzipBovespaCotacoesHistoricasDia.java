package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.ZipUtil;

import java.io.IOException;


public class ProcessUnzipBovespaCotacoesHistoricasDia {
    public static void execute() {
        try {
            FileUtil fileUtil = new FileUtil();
            ZipUtil.Unzip(fileUtil.getFolderCreateIfNotExists("/cotacoes/dia/zip").getPath() + "/COTAHIST_DIA.ZIP", fileUtil.getFolderCreateIfNotExists("/cotacoes/dia/txt").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
