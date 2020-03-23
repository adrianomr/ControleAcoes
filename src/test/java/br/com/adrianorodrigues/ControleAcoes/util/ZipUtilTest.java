package br.com.adrianorodrigues.ControleAcoes.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZipUtilTest {

    @Test
    void unzip() throws IOException {
        FileUtil.DeleteFiles("src/main/resources/cotacoes");
        ZipUtil.Unzip("target/COTAHIST_A2020.ZIP","src/main/resources/cotacoes");
        File file = new File("src/main/resources/cotacoes/COTAHIST_A2020.TXT");
        assertTrue(file.exists());
    }
}