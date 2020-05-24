package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Cotacao;
import br.com.adrianorodrigues.controleacoes.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CotacaoService {
    @Autowired
    private CotacaoRepository cotacaoRepository;

    public Cotacao insertCotacao(Cotacao cotacao) {
        return cotacaoRepository.save(cotacao);
    }

    public Cotacao updateCotacao(Long cotacaoId, Cotacao cotacaoRequest) {
        return cotacaoRepository.findById(cotacaoId)
                .map(cotacao -> {
                    cotacaoRequest.setId(cotacaoId);
                    return cotacaoRepository.save(cotacaoRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Cotacao not found with id " + cotacaoId));
    }

    public ResponseEntity deleteCotacao(Long cotacaoId) {
        cotacaoRepository.findById(cotacaoId)
                .map(cotacao -> {
                    cotacaoRepository.delete(cotacao);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Cotacao not found with id " + cotacaoId));
        return null;
    }

    public Page<Cotacao> getCotacaoList(Pageable pageable) {
        return cotacaoRepository.findAll(pageable);
    }

    public void insertListCotacao(ArrayList<Cotacao> cotacaoArrayList) {
        cotacaoRepository.saveAll(cotacaoArrayList);
        cotacaoRepository.flush();
    }

    public Cotacao getCotacaoAtual(Long idAcao) {
        return cotacaoRepository.getCotacaoAtual(idAcao);
    }
}
