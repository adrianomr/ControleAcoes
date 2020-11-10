package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    public Acao insertAcao(Acao acao) {
        return acaoRepository.save(acao);
    }

    public Acao updateAcao(Long acaoId, Acao acaoRequest) {
        acaoRequest.setId(acaoId);
        return acaoRepository.save(acaoRequest);
    }

    public Acao findById(Long acaoId){
        return acaoRepository
                .findById(acaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Acao not found with id " + acaoId));
    }

    public ResponseEntity<Object> deleteAcao(Long acaoId) {
        return acaoRepository.findById(acaoId)
                .map(acao -> {
                    acaoRepository.delete(acao);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Acao not found with id " + acaoId));
    }

    public void insertListAcao(List<Acao> acaoList) {
        acaoRepository.saveAll(acaoList);
        acaoRepository.flush();
    }

    public Page<Acao> getAcaoList(Pageable pageable) {
        return acaoRepository.findAll(pageable);
    }

    public Page<Acao> findAcaoListByPapelContaining(Pageable pageable, String papel) {
        return acaoRepository.findAllByPapelContaining(papel, pageable);
    }

    public Acao findAcaoByPapel(String papel) {
        return acaoRepository.findOneByPapel(papel);
    }

}
