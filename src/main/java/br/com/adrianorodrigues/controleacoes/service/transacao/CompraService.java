package br.com.adrianorodrigues.controleacoes.service.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompraService {
    @Autowired
    TransacaoService transacaoService;

    public void compraAcao(TransacaoDTO transacao) {
        transacaoService.saveTrasacao(transacao, TipoTransacao.COMPRA);
    }
}
