package br.com.adrianorodrigues.controleacoes.interfaces;

import java.util.List;

public interface CrudService<T> {
    List<T> find();

    T find(Long t);
    T save(T t);
    void delete(Long id);
}
