package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.util.ZipUtil;

import java.io.IOException;

public class ProcessUnzipBovespaCotacoesHistoricas {
    public static void execute() {
        try {
            ZipUtil.Unzip("src/main/resources/cotacoes/zip/COTAHIST_DIA.ZIP", "src/main/resources/cotacoes/txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
