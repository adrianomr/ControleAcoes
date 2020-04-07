package br.com.adrianorodrigues.ControleAcoes.dto;

import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;

import java.util.ArrayList;

public class ArrayListCotacaoDto {
    private static ArrayList<Cotacao> arrayListCotacaoDto = new ArrayList<>();

    public static ArrayList<Cotacao> getArrayListCotacaoDto() {
        return arrayListCotacaoDto;
    }

}
