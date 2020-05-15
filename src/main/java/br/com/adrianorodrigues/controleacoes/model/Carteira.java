package br.com.adrianorodrigues.controleacoes.model;

import java.util.HashMap;

public class Carteira {
    private HashMap<Long, Acao> acaoes = new HashMap();

    public HashMap<Long, Acao> getAcaoes() {
        return acaoes;
    }

    public void setAcaoes(HashMap<Long, Acao> acaoes) {
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
