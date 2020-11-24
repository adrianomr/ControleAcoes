package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.dto.RebalanceamentoAcaoDto;
import br.com.adrianorodrigues.controleacoes.mapper.RebalanceamentoAcaoDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.RebalanceamentoAcaoMapper;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.service.RebalanceamentoAcaoService;
import br.com.adrianorodrigues.controleacoes.service.UsuarioService;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rebalanceamento")
public class RebalanceamentoController {
    @Autowired
    RebalanceamentoAcaoService rebalanceamentoAcaoService;
    @Autowired
    AcaoService acaoService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public List<RebalanceamentoAcaoDto> getRebalanceamentoList(@RequestParam("usuario") Long idUsuario) {
        return rebalanceamentoAcaoService
                .findAllByUsuario(idUsuario)
                .stream()
                .map(rebalanceamentoAcao -> RebalanceamentoAcaoDtoMapper
                        .from(rebalanceamentoAcao)
                        .map())
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json")
    public void postRebalanceamento(@Valid @RequestBody RebalanceamentoAcaoDto rebalanceamentoAcaoDto) {
        Usuario usuario = usuarioService.findUsuarioById(rebalanceamentoAcaoDto.getIdUsuario());
        Acao acao = acaoService.findAcaoByPapel(rebalanceamentoAcaoDto.getPapel());
        RebalanceamentoAcao rebalanceamentoAcao = RebalanceamentoAcaoMapper
                .from(rebalanceamentoAcaoDto, acao, usuario)
                .map();
        rebalanceamentoAcaoService.save(rebalanceamentoAcao);
    }

    @PutMapping(consumes = "application/json")
    public void putRebalanceamento(@Valid @RequestBody RebalanceamentoAcaoDto rebalanceamentoAcaoDto) {
        Usuario usuario = usuarioService.findUsuarioById(rebalanceamentoAcaoDto.getIdUsuario());
        Acao acao = acaoService.findAcaoByPapel(rebalanceamentoAcaoDto.getPapel());
        RebalanceamentoAcao rebalanceamentoAcao = RebalanceamentoAcaoMapper
                .from(rebalanceamentoAcaoDto, acao, usuario)
                .map();
        rebalanceamentoAcaoService.save(rebalanceamentoAcao);
    }

    @DeleteMapping("{id}")
    public void deleteRebalanceamento(@PathVariable Long id) {
        rebalanceamentoAcaoService.delete(id);
    }

    @GetMapping("carteira")
    public CarteiraDTO getCarteiraParaRebalanceamento(@RequestParam("usuario") Long idUsuario) {
        return rebalanceamentoAcaoService.getCarteiraParaRebalanceamento(idUsuario);
    }
}
