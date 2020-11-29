package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.interfaces.CrudService;
import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import br.com.adrianorodrigues.controleacoes.repository.GrupoAcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoAcaoService implements CrudService<GrupoAcao> {

    @Autowired
    GrupoAcaoRepository grupoAcaoRepository;

    @Override
    public List<GrupoAcao> find() {
        return grupoAcaoRepository.findAll();
    }

    public GrupoAcao find(Long id) {
        return grupoAcaoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de ações não encontrado"));
    }

    public GrupoAcao save(GrupoAcao grupoAcao) {
        return grupoAcaoRepository.save(grupoAcao);
    }

    public void delete(Long id) {
        grupoAcaoRepository.deleteById(id);
    }

    public List<GrupoAcao> findByIdUsuario(Long idUsuario) {
        return grupoAcaoRepository.findAllByUsuarioId(idUsuario);
    }
}
