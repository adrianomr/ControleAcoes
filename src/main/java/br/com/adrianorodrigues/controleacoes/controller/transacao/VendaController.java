package br.com.adrianorodrigues.controleacoes.controller.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.service.transacao.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transacao")
public class VendaController {
    @Autowired
    VendaService vendaService;

    @PostMapping(value = "/venda", consumes = "application/json")
    public String postVenda(@Valid @RequestBody TransacaoDTO transacao) {
        vendaService.vendaAcao(transacao);
        return "Venda realizada com sucesso";
    }
}
