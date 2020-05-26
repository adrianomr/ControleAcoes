package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.ControleAcoesApplication;
import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest(classes = ControleAcoesApplication.class)
@ActiveProfiles({"test"})
class ProcessSalvaBovespaCotacoesHistoricasSequencialTest {
    @Autowired
    ProcessSalvaBovespaCotacoesHistoricasSequencial processSalvaBovespaCotacoesHistoricasSequencial;

    @Autowired
    ProcessSalvaAcoesHistoricasSequencial processSalvaAcoesHistoricasSequencial;

    @BeforeAll
    public static void antesTestes() {
        FileUtil fileUtil = new FileUtil();
        String folderName = "/cotacoes/txt";
        fileUtil.DeleteFiles(folderName);
        try {
            fileUtil.writeToFIle(folderName + "/cotacao-teste.txt", "00COTAHIST.1986BOVESPA 19991210                                                                                                                                                                                                                      \n" +
                    "012020010202AALR3       010ALLIAR      ON      NM   R$  000000000182900000000019000000000001828000000000186800000000019000000000001899000000000190102443000000000000585800000000001094619600000000000000009999123100000010000000000000BRAALRACNOR6101\n" +
                    "00COTAHIST.1986BOVESPA 19991210                                                                                                                                                                                                                      ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void execute() {
        processSalvaAcoesHistoricasSequencial.execute();
        processSalvaBovespaCotacoesHistoricasSequencial.execute();
    }
}