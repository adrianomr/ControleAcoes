package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.interfaces.CrudService;
import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import br.com.adrianorodrigues.controleacoes.repository.SubscricaoRepository;
import br.com.adrianorodrigues.controleacoes.service.transacao.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscricaoService implements CrudService<Subscricao> {

    @Autowired
    SubscricaoRepository subscricaoRepository;
    @Autowired
    TransacaoService transacaoService;

    @Override
    public List<Subscricao> find() {
        return subscricaoRepository.findAll();
    }

    public List<Subscricao> findAllByUsuario(Long idUsuario) {
        return subscricaoRepository.findAllByUsuarioId(idUsuario);
    }

    @Override
    public Subscricao find(Long id) {
        return subscricaoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscrição não encontrada"));
    }

    @Override
    public Subscricao save(Subscricao subscricao) {
        return subscricaoRepository.save(subscricao);
    }

    @Override
    public void delete(Long id) {
        subscricaoRepository.deleteById(id);
    }
}
