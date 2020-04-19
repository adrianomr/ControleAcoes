package br.com.adrianorodrigues.ControleAcoes.dto;

import br.com.adrianorodrigues.ControleAcoes.model.Acao;

import java.util.HashMap;

public class HashMapAcaoDto {
    private static HashMap<String, Acao> hashAcaoDto = new HashMap<>();

    public static HashMap<String, Acao> getHashAcaoDto() {
        return hashAcaoDto;
    }

    public static void limpaLista() {
        hashAcaoDto = null;
    }
}
