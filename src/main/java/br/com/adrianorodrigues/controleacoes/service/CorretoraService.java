package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.interfaces.CrudService;
import br.com.adrianorodrigues.controleacoes.model.Corretora;
import br.com.adrianorodrigues.controleacoes.repository.CorretoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CorretoraService implements CrudService<Corretora> {
    
    @Autowired
    CorretoraRepository corretoraRepository;

    @Override
    public List<Corretora> find() {
        return corretoraRepository.findAll();
    }

    public Corretora find(Long id) {
        return corretoraRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corretora nao encontrado"));
    }

    public Corretora save(Corretora corretora) {
        return corretoraRepository.save(corretora);
    }

    public void delete(Long id) {
        corretoraRepository.deleteById(id);
    }
}
