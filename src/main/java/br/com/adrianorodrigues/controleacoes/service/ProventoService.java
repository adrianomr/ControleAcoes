package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.ProventoDTO;
import br.com.adrianorodrigues.controleacoes.mapper.ProventoDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.ProventoMapper;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Provento;
import br.com.adrianorodrigues.controleacoes.repository.ProventoRepository;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import br.com.adrianorodrigues.controleacoes.service.acao.CotacaoBovespaRedisService;
import br.com.adrianorodrigues.controleacoes.service.b3.AnosEmCacheSet;
import br.com.adrianorodrigues.controleacoes.service.b3.FetchB3DataAwsQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProventoService {
    @Autowired
    private ProventoRepository proventoRepository;
    @Autowired
    private FetchB3DataAwsQueue fetchB3DataAwsQueue;
    @Autowired
    private AnosEmCacheSet anosEmCacheSet;
    @Autowired
    private CotacaoBovespaRedisService cotacaoBovespaRedisService;
    @Autowired
    AcaoService acaoService;

    public Set<ProventoDTO> getProventoSetByAcao(Long idAcao) {
        Set<ProventoDTO> proventoDTOSet = new HashSet<>();
        Acao acao = acaoService.findById(idAcao);
        Set<Provento> proventoSet = proventoRepository.findAllByAcao(acao);
        if (!proventoSet.isEmpty()) {
            proventoDTOSet = proventoSet
                    .stream()
                    .map(
                            provento -> ProventoDtoMapper
                                    .from(provento)
                                    .map()
                    )
                    .collect(Collectors.toSet());
        }
        return proventoDTOSet;
    }

    public ProventoDTO save(ProventoDTO proventoDTO) {
        Acao acao = acaoService.findAcaoByPapel(proventoDTO.getPapel());
        Provento provento = proventoRepository
                .save(
                        ProventoMapper
                                .from(proventoDTO, acao)
                                .map()
                );
        return ProventoDtoMapper.from(provento).map();
    }

    public void delete(Long id) {
        proventoRepository.deleteById(id);
    }
}
