package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FileUtilTest {

    @Test
    void readFile() throws IOException {
        String data = FileUtil.readFile("src/main/resources/cotacoes/txt/COTAHIST_A2020.TXT");
        String[] cotacoes = data.split("\n");
        for (int i = 1; i < cotacoes.length-1; i++){
            System.out.println(CotacoesBovespaDtoBuilder.build(cotacoes[i]));
        }
    }

    @Test
    void listFilesForFolder() {
        System.out.println(FileUtil.listFilesForFolder(new File("src/main/resources/cotacoes/txt")));
    }
}