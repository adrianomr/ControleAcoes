package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.CotacoesBovespaDto;
import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.mapper.EmpresaMantenedoraDtoMapper;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
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
        int ano = LocalDate.now().getYear() - 1;
        Logger.getGlobal().info("idAcao" + idAcao);
        Acao acao = acaoService.findById(idAcao);
        Set<EmpresaMantenedora> empresaMantenedoraSet = empresaMantenedoraRepository.findAllByAcao(acao);
        if (empresaMantenedoraSet.isEmpty()) {
            if (anosEmCacheSet.getAnosEmCache().contains(ano)) {
                log.info("Buscando empresa mantenedora no redis...");
                empresaMantenedoraSet = getEmpresaMantenedoraSetFromRedis(ano, acao);
            } else {
                fetchB3DataAwsQueue.sendMessage(ano);
            }
        }
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

    private Set<EmpresaMantenedora> getEmpresaMantenedoraSetFromRedis(int ano, Acao acao) {
        Set<EmpresaMantenedora> empresaMantenedoraSet = new HashSet<>();
        Long size = cotacaoBovespaRedisService.getSize(ano);
        Long pageSize = 20L;
        for (long i = 0; i * pageSize < size; i++) {
            List<CotacoesBovespaDto> cotacoesBovespaDtoList = cotacaoBovespaRedisService.
                    findByIdPaginated(ano, pageSize, i);
            for(CotacoesBovespaDto cotacoesBovespaDto : cotacoesBovespaDtoList){
                if(cotacoesBovespaDto.getCodNegociacaoPapel().equalsIgnoreCase(acao.getPapel())){
                    EmpresaMantenedora empresaMantenedora = EmpresaMantenedora
                            .builder()
                            .descricao(cotacoesBovespaDto.getNomeResumidoEmpresaEmissora())
                            .dataCadastro(LocalDateTime.now())
                            .acao(acao)
                            .build();
                    empresaMantenedoraSet.add(empresaMantenedora);
                }
            }
        }
        empresaMantenedoraRepository.saveAll(empresaMantenedoraSet);
        return empresaMantenedoraSet;
    }

    public EmpresaMantenedoraDTO save(EmpresaMantenedoraDTO empresaMantenedoraDTO) {
        return null;
    }
}
