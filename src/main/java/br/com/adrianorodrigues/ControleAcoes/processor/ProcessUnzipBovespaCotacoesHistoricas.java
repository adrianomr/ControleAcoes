package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.util.ZipUtil;

import java.io.IOException;

public class ProcessUnzipBovespaCotacoesHistoricas {
    public static void execute() {
        try {
            //todo scheduled function to download, unzip and save daily values
            ZipUtil.Unzip("src/main/resources/cotacoes/dia/zip/COTAHIST_DIA.ZIP", "src/main/resources/cotacoes/dia/txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
