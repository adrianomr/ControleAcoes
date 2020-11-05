package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.mapper.EmpresaMantenedoraDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.EmpresaMantenedoraMapper;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.EmpresaMantenedora;
import br.com.adrianorodrigues.controleacoes.repository.EmpresaMantenedoraRepository;
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
public class EmpresaMantenedoraService {
    @Autowired
    private EmpresaMantenedoraRepository empresaMantenedoraRepository;
    @Autowired
    private FetchB3DataAwsQueue fetchB3DataAwsQueue;
    @Autowired
    private AnosEmCacheSet anosEmCacheSet;
    @Autowired
    private CotacaoBovespaRedisService cotacaoBovespaRedisService;
    @Autowired
    AcaoService acaoService;

    public Set<EmpresaMantenedoraDTO> getEmpresaControladora(Long idAcao) {
        Set<EmpresaMantenedoraDTO> empresaMantenedoraDTOS = new HashSet<>();
        Acao acao = acaoService.findById(idAcao);
        Set<EmpresaMantenedora> empresaMantenedoraSet = empresaMantenedoraRepository.findAllByAcao(acao);
        if (!empresaMantenedoraSet.isEmpty()) {
            empresaMantenedoraDTOS = empresaMantenedoraSet
                    .stream()
                    .map(
                            empresaMantenedora -> EmpresaMantenedoraDtoMapper
                                    .from(empresaMantenedora)
                                    .map()
                    )
                    .collect(Collectors.toSet());
        }
        return empresaMantenedoraDTOS;
    }

    public EmpresaMantenedoraDTO save(EmpresaMantenedoraDTO empresaMantenedoraDTO) {
        Acao acao = acaoService.findById(empresaMantenedoraDTO.getIdAcao());
        EmpresaMantenedora empresaMantenedora = empresaMantenedoraRepository
                .save(
                        EmpresaMantenedoraMapper
                                .from(empresaMantenedoraDTO, acao)
                                .map()
                );
        return EmpresaMantenedoraDtoMapper.from(empresaMantenedora).map();
    }

    public void delete(Long id) {
        empresaMantenedoraRepository.deleteById(id);
    }
}
