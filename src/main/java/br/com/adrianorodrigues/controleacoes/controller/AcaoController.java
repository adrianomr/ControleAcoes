package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("acao")
public class AcaoController {
    @Autowired
    AcaoService acaoService;

    @GetMapping()
    public Page<Acao> getAcoes(Pageable pageable, @RequestParam("papel") String papel) {
        return acaoService.findAcaoListByPapelContaining(pageable, papel);
    }

    @GetMapping("{acao}/empresa-controladora")
    public Set<EmpresaMantenedoraDTO> getContas(@PathVariable String acao) {
        return acaoService.getEmpresaControladora(acao);
    }
}
