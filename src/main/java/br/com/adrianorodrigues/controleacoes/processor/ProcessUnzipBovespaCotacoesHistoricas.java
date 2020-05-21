package br.com.adrianorodrigues.controleacoes.processor;

import br.com.adrianorodrigues.controleacoes.util.FileUtil;
import br.com.adrianorodrigues.controleacoes.util.ZipUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.controleacoes.util.ConcurrencyUtils.stop;


public class ProcessUnzipBovespaCotacoesHistoricas {
    public static int execute() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        AtomicInteger atomicInt = new AtomicInteger(0);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        IntStream.range(1986, anoAtual)
                .forEach(i -> {
                    Runnable task = () -> {
                        try {
                            FileUtil fileUtil = new FileUtil();
                            ZipUtil.Unzip(fileUtil.getFolderCreateIfNotExists("/cotacoes/zip").getPath() + "/COTAHIST_A" + i + ".ZIP", fileUtil.getFolderCreateIfNotExists("/cotacoes/txt").getPath());
                            atomicInt.addAndGet(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    };
                    executor.submit(task);
                });
        stop(executor);
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
