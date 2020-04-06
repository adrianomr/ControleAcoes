package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import br.com.adrianorodrigues.ControleAcoes.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.ControleAcoes.util.ConcurrencyUtils.stop;

public class ProcessSalvaBovespaCotacoesHistoricas {
    public static int execute() {
        List<String> files = FileUtil.listFilesForFolder(new File("src/main/resources/cotacoes/txt"));
        ExecutorService executor = Executors.newFixedThreadPool(2);
        AtomicInteger atomicInt = new AtomicInteger(0);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        IntStream.range(0, files.size())
                .forEach(i -> {
                    Runnable task = () -> {
                        System.out.println("Running task " + i);
                        try {
                            String data = FileUtil.readFile("src/main/resources/cotacoes/txt/" + files.get(i));
                            String[] cotacoes = data.split("\n");
                            for (int x = 1; x < cotacoes.length - 1; x++) {
                                CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build(cotacoes[i]);
                                Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                                Cotacao cotacao = CotacaoFromCotacoesBovespaBuilder.build(acao, cotacoesBovespaDto);
                            }
                            atomicInt.addAndGet(1);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Finished task " + i);
                    };
                    executor.submit(task);
                });
        stop(executor);
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
