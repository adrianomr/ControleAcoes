package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.client.CotacaoCliente;
import br.com.adrianorodrigues.ControleAcoes.util.ZipUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.ControleAcoes.util.ConcurrencyUtils.stop;

public class ProcessUnzipBovespaCotacoesHistoricas {
    public static int execute() {
        ExecutorService executor = Executors.newWorkStealingPool();
        AtomicInteger atomicInt = new AtomicInteger(0);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        IntStream.range(1986, anoAtual)
                .forEach(i -> {
                    try {
                        ZipUtil.Unzip("src/main/resources/cotacoes/zip/COTAHIST_A"+i+".ZIP","src/main/resources/cotacoes/txt");
                        atomicInt.addAndGet(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        stop(executor);
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
