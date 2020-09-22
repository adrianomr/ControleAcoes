package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.service.RebalanceamentoAcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rebalanceamento")
public class RebalanceamentoController {
    @Autowired
    RebalanceamentoAcaoService rebalanceamentoAcaoService;

    @GetMapping()
    public List<RebalanceamentoAcao> getRebalanceamentoList(@RequestParam("usuario") Long idUsuario) {
        return rebalanceamentoAcaoService.findAllByUsuario(idUsuario);
    }

    @PostMapping(consumes = "application/json")
    public void postRebalanceamento(@Valid @RequestBody RebalanceamentoAcao rebalanceamentoAcao) {
        rebalanceamentoAcaoService.save(rebalanceamentoAcao);
    }

    @PutMapping(consumes = "application/json")
    public void putRebalanceamento(@Valid @RequestBody RebalanceamentoAcao rebalanceamentoAcao) {
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
