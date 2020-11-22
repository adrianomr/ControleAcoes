package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.SubscricaoDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class SubscricaoMapper {
    Subscricao subscricao;

    public static SubscricaoMapper from(SubscricaoDTO subscricaoDTO, Acao acao, Carteira carteira) {
        Subscricao subscricao = Subscricao
                .builder()
                .id(subscricaoDTO.getId())
                .data(subscricaoDTO.getData())
                .acao(acao)
                .usuario(carteira.getUsuario())
                .carteira(carteira)
                .quantidade(subscricaoDTO.getQuantidade())
                .valor(BigDecimal.valueOf(subscricaoDTO.getValor()))
                .build();
        subscricao.setTransacao(TransacaoMapper.from(subscricao).map());
        return SubscricaoMapper
                .builder()
                .subscricao(subscricao)
                .build();
    }

    public Subscricao map() {
        return subscricao;
    }
}
