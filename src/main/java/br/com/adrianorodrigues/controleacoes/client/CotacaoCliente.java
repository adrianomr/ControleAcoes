package br.com.adrianorodrigues.controleacoes.client;

import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.LogUtil;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CotacaoCliente {

    private CotacaoCliente() {

    }

    public static boolean getCotacoes(int ano) {
        FileUtil fileUtil = new FileUtil();
        try (BufferedInputStream in = new BufferedInputStream(new URL("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_A" + ano + ".ZIP").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileUtil.getFolderCreateIfNotExists("/cotacoes/zip") + "/COTAHIST_A" + ano + ".ZIP")) {
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
        FileUtil fileUtil = new FileUtil();
        LocalDate dataAtual = LocalDate.now().minusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY");
        try (BufferedInputStream in = new BufferedInputStream(new URL("http://bvmf.bmfbovespa.com.br/InstDados/SerHist/COTAHIST_D" + formatter.format(dataAtual) + ".ZIP").openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileUtil.getFolderCreateIfNotExists("/cotacoes/dia/zip") + "/COTAHIST_DIA.ZIP")) {
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
