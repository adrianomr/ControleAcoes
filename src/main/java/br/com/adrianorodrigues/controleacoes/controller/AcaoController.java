package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("acao")
public class AcaoController {
    @Autowired
    AcaoService acaoService;

    @GetMapping()
    public Page<Acao> getAcoes(Pageable pageable, @RequestParam("papel") String papel) {
        return acaoService.findAcaoListByPapelContaining(pageable, papel);
    }
}
