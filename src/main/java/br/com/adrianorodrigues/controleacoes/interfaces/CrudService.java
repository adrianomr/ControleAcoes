package br.com.adrianorodrigues.controleacoes.interfaces;

import java.util.Set;

public interface CrudService<T> {
    Set<T> find();
    T find(Long t);
    T save(T t);
    void delete(Long id);
}
