package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import lombok.Builder;

@Builder
public class CarteiraDtoMapper {
    CarteiraDTO carteiraDTO;

    public static CarteiraDtoMapper from(Carteira carteira) {
        CarteiraDTO carteiraDTO = CarteiraDTO
                .builder()
                .id(carteira.getId())
                .idCorretora(carteira.getCorretora().getId())
                .idUsuario(carteira.getUsuario().getId())
                .build();
        return CarteiraDtoMapper
                .builder()
                .carteiraDTO(carteiraDTO)
                .build();
    }

    public CarteiraDTO map() {
        return carteiraDTO;
    }
}
