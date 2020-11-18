package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Corretora;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.Builder;

@Builder
public class CarteiraMapper {
    Carteira carteira;

    public static CarteiraMapper from(CarteiraDTO carteiraDTO, Corretora corretora, Usuario usuario) {
        Carteira carteira = Carteira
                .builder()
                .id(carteiraDTO.getId())
                .corretora(corretora)
                .usuario(usuario)
                .build();
        return CarteiraMapper
                .builder()
                .carteira(carteira)
                .build();
    }

    public Carteira map() {
        return carteira;
    }
}
