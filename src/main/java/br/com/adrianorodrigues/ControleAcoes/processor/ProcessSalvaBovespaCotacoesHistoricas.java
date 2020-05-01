package br.com.adrianorodrigues.ControleAcoes.processor;

import br.com.adrianorodrigues.ControleAcoes.builder.AcaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacaoFromCotacoesBovespaBuilder;
import br.com.adrianorodrigues.ControleAcoes.builder.CotacoesBovespaDtoBuilder;
import br.com.adrianorodrigues.ControleAcoes.dto.ArrayListCotacaoDto;
import br.com.adrianorodrigues.ControleAcoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.ControleAcoes.dto.HashMapAcaoDto;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import br.com.adrianorodrigues.ControleAcoes.service.AcaoService;
import br.com.adrianorodrigues.ControleAcoes.service.CotacaoService;
import br.com.adrianorodrigues.ControleAcoes.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static br.com.adrianorodrigues.ControleAcoes.util.ConcurrencyUtils.stop;

@Component
public class ProcessSalvaBovespaCotacoesHistoricas {
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private CotacaoService cotacaoService;
    Semaphore semaphore = new Semaphore(1);

    public int execute() {
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
                                CotacoesBovespaDto cotacoesBovespaDto = CotacoesBovespaDtoBuilder.build(cotacoes[x]);
                                Acao acao = AcaoFromCotacoesBovespaBuilder.build(cotacoesBovespaDto);
                                semaphore.acquire();
                                if (!HashMapAcaoDto.getHashAcaoDto().containsKey(acao.getPapel())) {
                                    acaoService.insertAcao(acao);
                                    HashMapAcaoDto.getHashAcaoDto().put(acao.getPapel(), acao);
                                }
                                Cotacao cotacao = CotacaoFromCotacoesBovespaBuilder.build(HashMapAcaoDto.getHashAcaoDto().get(acao.getPapel()), cotacoesBovespaDto);
                                semaphore.release();
                                if (cotacao != null && acao != null)
                                    cotacaoService.insertCotacao(cotacao);

                            }
                            atomicInt.addAndGet(1);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Finished task " + i);
                    };
                    executor.submit(task);
                });
        stop(executor);
        System.out.println("HASHMAPSIZE: " + HashMapAcaoDto.getHashAcaoDto().size());
        cotacaoService.insertListCotacao(ArrayListCotacaoDto.getArrayListCotacaoDto());
        HashMapAcaoDto.limpaLista();
        System.out.println(atomicInt.get());
        return atomicInt.get();
    }
}
