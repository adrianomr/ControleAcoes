package br.com.adrianorodrigues.ControleAcoes.model;

import java.util.HashMap;

public class Carteira {
    private HashMap<Integer, Acao> acaoes = new HashMap();

    public HashMap<Integer, Acao> getAcaoes() {
        return acaoes;
    }

    public void setAcaoes(HashMap<Integer, Acao> acaoes) {
        this.acaoes = acaoes;
    }

    public void addAcao(Acao acao){
        getAcaoes().put(acao.getId(), acao);
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "acaoes=" + acaoes +
                '}';
    }
}
