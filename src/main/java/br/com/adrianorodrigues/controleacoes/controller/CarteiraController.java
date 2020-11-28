package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.dto.RiscoDto;
import br.com.adrianorodrigues.controleacoes.mapper.CarteiraDtoMapper;
import br.com.adrianorodrigues.controleacoes.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("carteira")
public class CarteiraController {
    @Autowired
    CarteiraService carteiraService;

    @GetMapping
    public Set<CarteiraDTO> get(@RequestParam("idUsuario") Long idUsuario) {
        return carteiraService.findCarteiraByUsuario(idUsuario)
                .stream()
                .map(carteira -> CarteiraDtoMapper
                        .from(carteira)
                        .map())
                .collect(Collectors.toSet());
    }

    @GetMapping("risco")
    public RiscoDto getRisco(@RequestParam("idUsuario") Long idUsuario,
                             @RequestParam("riscoBaixoMedio") Double riscoBaixoMedio,
                             @RequestParam("riscoMedioAlto") Double riscoMedioAlto) {
        return carteiraService.getRisco(idUsuario, riscoBaixoMedio, riscoMedioAlto);
    }

    @PostMapping(consumes = "application/json")
    public CarteiraDTO post(@Valid @RequestBody CarteiraDTO carteiraDTO) {
        return CarteiraDtoMapper.from(carteiraService.save(carteiraDTO)).map();
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public CarteiraDTO put(@PathVariable Long id, @Valid @RequestBody CarteiraDTO carteiraDTO) {
        carteiraDTO.setId(id);
        return CarteiraDtoMapper.from(carteiraService.save(carteiraDTO)).map();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        carteiraService.delete(id);
    }
}
