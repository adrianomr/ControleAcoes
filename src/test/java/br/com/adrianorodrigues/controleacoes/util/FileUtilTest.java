package br.com.adrianorodrigues.controleacoes.util;

import br.com.adrianorodrigues.controleacoes.builder.CotacoesBovespaDtoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class FileUtilTest {

    @Test
    void readFile() throws IOException {
        File file = FileUtil.getFileCreateIfNotExists("/cotacoes/txt/COTAHIST_A2020.TXT");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("012020010202AALR3       010ALLIAR      ON      NM   R$  000000000182900000000019000000000001828000000000186800000000019000000000001899000000000190102443000000000000585800000000001094619600000000000000009999123100000010000000000000BRAALRACNOR6101");
        writer.close();
        String data = FileUtil.readFile("/cotacoes/txt/COTAHIST_A2020.TXT");
        String[] cotacoes = data.split("\n");
        for (int i = 1; i < cotacoes.length - 1; i++) {
            System.out.println(CotacoesBovespaDtoBuilder.build(cotacoes[i]));
        }
    }

    @Test
    void listFilesForFolder() {
        List fileList = FileUtil.listFilesForFolder("/cotacoes/txt");
        System.out.println(FileUtil.listFilesForFolder("/cotacoes/txt"));
        Assertions.assertNotNull(fileList);
    }

    @Test
    void createFolderIfNotExists() {
        File file = new File(FileUtil.PATH + "/asdasd");
        System.out.println(FileUtil.PATH + "/asdasd");
        FileUtil.createFolderIfNotExists(file);
        Assertions.assertTrue(file.exists());
    }
}