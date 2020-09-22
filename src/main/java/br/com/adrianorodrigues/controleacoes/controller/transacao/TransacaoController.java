package br.com.adrianorodrigues.controleacoes.controller.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.service.transacao.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transacao")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;

    @GetMapping()
    public List<TransacaoDTO> getTransacoes() {
        return transacaoService.findAllTransacaoes();
    }

    @DeleteMapping("{id}")
    public void deleteTrancao(@PathVariable Long id) {
        transacaoService.delete(id);
    }

}
