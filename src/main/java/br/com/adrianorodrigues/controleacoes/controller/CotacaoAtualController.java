package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CotacaoAtualDTO;
import br.com.adrianorodrigues.controleacoes.service.CotacaoAtualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("")
public class CotacaoAtualController {
    @Autowired
    CotacaoAtualService cotacaoAtualService;

    @GetMapping("acao/{acao}/cotacao/atual")
    public CotacaoAtualDTO getContas(@PathVariable String acao) throws IOException {
        return cotacaoAtualService.getCotacaoAtual(acao);
    }
}
