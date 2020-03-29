package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.client.CotacaoCliente;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.ControleAcoes.util.ConcurrencyUtils.stop;

public class ProcessDownloadBovespaCotacoesHistoricas {
    public static int execute() {
        ExecutorService executor = Executors.newWorkStealingPool();
        AtomicInteger atomicInt = new AtomicInteger(0);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        IntStream.range(1986, anoAtual)
                .forEach(i -> {
                    CotacaoCliente.getCotacoes(i);
                    atomicInt.addAndGet(1);
                });
        stop(executor);
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
