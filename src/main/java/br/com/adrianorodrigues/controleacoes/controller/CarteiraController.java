package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario/{idUsuario}")
public class CarteiraController {
    @Autowired
    CarteiraService carteiraService;

    @GetMapping("carteira")
    public CarteiraDTO getCarteira(Pageable pageable, @PathVariable("idUsuario") Long idUsuario) {
        return carteiraService.getCarteira(idUsuario);
    }
}
