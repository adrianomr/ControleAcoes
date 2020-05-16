package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.ZipUtil;

import java.io.IOException;

public class ProcessUnzipBovespaCotacoesHistoricas {
    public static void execute() {
        try {
            ZipUtil.Unzip(FileUtil.getFolderCreateIfNotExists("/cotacoes/zip").getPath() + "/COTAHIST_DIA.ZIP", FileUtil.getFolderCreateIfNotExists("/cotacoes/txt").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
