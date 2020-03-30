package br.com.adrianorodrigues.ControleAcoes.util;

import br.com.adrianorodrigues.ControleAcoes.builder.CotacoesBovespaDtoBuilder;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void readFile() throws IOException {
        String data = FileUtil.readFile("src/main/resources/cotacoes/txt/COTAHIST_A2020.TXT");
        String[] cotacoes = data.split("\n");
        for (int i = 1; i < cotacoes.length-1; i++){
            System.out.println(CotacoesBovespaDtoBuilder.build(cotacoes[i]));
        }
    }
}