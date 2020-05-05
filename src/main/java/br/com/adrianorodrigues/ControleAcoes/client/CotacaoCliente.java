package br.com.adrianorodrigues.ControleAcoes.client;

import br.com.adrianorodrigues.ControleAcoes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CotacaoCliente {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static boolean getCotacoes(int ano){
        try (BufferedInputStream in = new BufferedInputStream(new URL("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_A" + ano + ".ZIP").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/cotacoes/zip/COTAHIST_A" + ano + ".ZIP")) {
            byte[] dataBuffer = new byte[1024];
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

    public static boolean getCotacaoDia() {
        Date dataAtual = new Date();
        SimpleDateFormat x = new SimpleDateFormat("DDmmYYY");
        try (BufferedInputStream in = new BufferedInputStream(new URL("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_D" + x.format(dataAtual) + ".ZIP").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/cotacoes/dia/zip/COTAHIST_DIA.ZIP")) {
            byte[] dataBuffer = new byte[1024];
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
