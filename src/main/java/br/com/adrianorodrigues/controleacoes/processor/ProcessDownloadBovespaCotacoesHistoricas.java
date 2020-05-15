package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.client.CotacaoCliente;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.controleacoes.util.ConcurrencyUtils.stop;

public class ProcessDownloadBovespaCotacoesHistoricas {
    public static int execute() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        AtomicInteger atomicInt = new AtomicInteger(0);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        IntStream.range(1986, anoAtual)
                .forEach(i -> {
                    Runnable task = () -> {
                        CotacaoCliente.getCotacoes(i);
                        atomicInt.addAndGet(1);
                    };
                    executor.submit(task);
                });
        stop(executor);
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
