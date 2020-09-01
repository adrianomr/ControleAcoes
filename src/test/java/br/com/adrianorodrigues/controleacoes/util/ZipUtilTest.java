package br.com.adrianorodrigues.controleacoes.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ZipUtilTest {
    @Test
    void unzip() throws IOException {
        FileUtil fileUtil = new FileUtil();
        fileUtil.deleteFiles("src/main/resources/cotacoes/txt");
        ZipUtil.Unzip("src/main/resources/cotacoes/zip/COTAHIST_A2020.ZIP", "src/main/resources/cotacoes/txt");
        File file = new File("src/main/resources/cotacoes/txt/COTAHIST_A2020.TXT");
        assertTrue(file.exists());
    }
}