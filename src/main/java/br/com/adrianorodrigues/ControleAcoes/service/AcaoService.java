package br.com.adrianorodrigues.ControleAcoes.service;

import br.com.adrianorodrigues.ControleAcoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import br.com.adrianorodrigues.ControleAcoes.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    public Acao insertAcao(Acao acao) {
        return acaoRepository.save(acao);
    }

    public Acao updateAcao(Long acaoId, Acao acaoRequest) {
        return acaoRepository.findById(acaoId)
                .map(acao -> {
                    acaoRequest.setId(acaoId);
                    return acaoRepository.save(acaoRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Acao not found with id " + acaoId));
    }

    public ResponseEntity deleteAcao(Long acaoId) {
        acaoRepository.findById(acaoId)
                .map(acao -> {
                    acaoRepository.delete(acao);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Acao not found with id " + acaoId));
        return null;
    }

    public HashMap<String, Acao> insertMapAcao(HashMap<String, Acao> acaoHashMap) {
        for (String papel : acaoHashMap.keySet()) {
            acaoRepository.saveAndFlush(acaoHashMap.get(papel));
        }
        return acaoHashMap;
    }
    //https://howtodoinjava.com/hibernate/hibernate-criteria-queries-tutorial/

    public Page<Acao> getAcaoList(Pageable pageable) {
        return acaoRepository.findAll(pageable);
    }
}
