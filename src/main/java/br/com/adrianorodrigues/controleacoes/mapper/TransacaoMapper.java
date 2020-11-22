package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class TransacaoMapper {
    Transacao transacao;

    public static TransacaoMapper from(Subscricao subscricao) {
        Transacao transacao = Transacao
                .builder()
                .id(subscricao.getId())
                .data(subscricao.getData().atStartOfDay())
                .acao(subscricao.getAcao())
                .valor(subscricao.getValor())
                .carteira(subscricao.getCarteira())
                .usuario(subscricao.getUsuario())
                .quantidade(subscricao.getQuantidade())
                .tipoTransacao(TipoTransacao.COMPRA)
                .build();
        return TransacaoMapper
                .builder()
                .transacao(transacao)
                .build();
    }

    public static TransacaoMapper from(TransacaoDTO transacaoDTO, TipoTransacao tipoTransacao, Usuario usuario, Carteira carteira, Acao acao) {
        Transacao transacao = new Transacao();
        transacao.setData(transacaoDTO.getData());
        transacao.setAcao(acao);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setQuantidade(transacaoDTO.getQuantidade());
        transacao.setValor(BigDecimal.valueOf(transacaoDTO.getValor()));
        transacao.setUsuario(usuario);
        transacao.setCarteira(carteira);
        return TransacaoMapper
                .builder()
                .transacao(transacao)
                .build();
    }

    public Transacao map() {
        return transacao;
    }
}
