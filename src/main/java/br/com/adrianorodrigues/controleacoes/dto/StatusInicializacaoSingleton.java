package br.com.adrianorodrigues.controleacoes.dto;

import java.util.concurrent.atomic.AtomicBoolean;

public class StatusInicializacaoSingleton {
    static StatusInicializacaoSingleton singleton = new StatusInicializacaoSingleton();
    private final AtomicBoolean inicializou;

    private StatusInicializacaoSingleton() {
        inicializou = new AtomicBoolean(false);
    }

    public static StatusInicializacaoSingleton getSingleton() {
        return singleton;
    }

    public boolean isInicializou() {
        return inicializou.get();
    }

    public void setInicializou() {
        inicializou.set(true);
    }
}
