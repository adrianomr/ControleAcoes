package br.com.adrianorodrigues.ControleAcoes.util;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void readFile() throws IOException {
        String data = FileUtil.readFile("src/main/resources/cotacoes/txt/COTAHIST_A2020.TXT");
        System.out.println(data);
    }
}