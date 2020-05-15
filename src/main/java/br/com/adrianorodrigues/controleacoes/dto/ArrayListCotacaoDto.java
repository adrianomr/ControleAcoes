package br.com.adrianorodrigues.controleacoes.dto;

import br.com.adrianorodrigues.controleacoes.model.Cotacao;

import java.util.ArrayList;

public class ArrayListCotacaoDto {
    private static ArrayList<Cotacao> arrayListCotacaoDto = new ArrayList<>();

    ArrayListCotacaoDto() {
    }

    public static ArrayList<Cotacao> getArrayListCotacaoDto() {
        return arrayListCotacaoDto;
    }

    public static void limpaLista() {
        arrayListCotacaoDto = new ArrayList<>();
    }
}
