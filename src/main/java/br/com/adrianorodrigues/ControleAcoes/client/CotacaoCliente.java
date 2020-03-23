package br.com.adrianorodrigues.ControleAcoes.client;

import br.com.adrianorodrigues.ControleAcoes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class CotacaoCliente {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static boolean getCotacoes(){
        try (BufferedInputStream in = new BufferedInputStream(new URL("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_A2020.ZIP").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/cotacoes/zip/COTAHIST_A2020.ZIP")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
            LogUtil.getLogger().warn("Não foi possível ler o arquivo", e);
            return false;
        }
        return true;
    }
}
