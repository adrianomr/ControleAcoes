package br.com.adrianorodrigues.controleacoes.controller.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.service.transacao.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transacao")
public class CompraController {
    @Autowired
    CompraService compraService;

    @PostMapping(value = "/compra", consumes = "application/json")
    public String postCompra(@Valid @RequestBody TransacaoDTO transacao) {
        compraService.compraAcao(transacao);
        return "Compra realizada com sucesso";
    }

}
