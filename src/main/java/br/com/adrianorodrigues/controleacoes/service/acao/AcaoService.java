package br.com.adrianorodrigues.controleacoes.service.acao;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.repository.AcaoRepository;
import br.com.adrianorodrigues.controleacoes.service.b3.AnosEmCacheSet;
import br.com.adrianorodrigues.controleacoes.service.b3.FetchB3DataAwsQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
@Slf4j
public class AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired
    FetchB3DataAwsQueue fetchB3DataAwsQueue;
    @Autowired
    AnosEmCacheSet anosEmCacheSet;

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
        int ano = LocalDate.now().getYear() - 1;
        Logger.getGlobal().info(papel);
        if (anosEmCacheSet.getAnosEmCache().contains(ano)) {
            //TODO: Buscar info no redis
            log.info("Buscar info no redis...");
        } else {
            fetchB3DataAwsQueue.sendMessage(ano);
        }
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
