package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired FetchEmpresaMantenedoraAwsQueue fetchEmpresaMantenedoraAwsQueue;

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

    public Set<EmpresaMantenedoraDTO> getEmpresaControladora(String papel) {
        Logger.getGlobal().info(papel);
        Acao acao = findAcaoByPapel(papel);
        fetchEmpresaMantenedoraAwsQueue.sendMessage(acao);
        Set<EmpresaMantenedoraDTO> empresaMantenedoraDTOS = new HashSet<>();
        empresaMantenedoraDTOS.add(
                EmpresaMantenedoraDTO
                        .builder()
                        .nome("Teste")
                        .cnpj("Teste")
                        .build());
        return empresaMantenedoraDTOS;
    }
}