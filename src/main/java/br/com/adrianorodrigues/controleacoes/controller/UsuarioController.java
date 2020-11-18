package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CarteiraDTO;
import br.com.adrianorodrigues.controleacoes.dto.UsuarioDTO;
import br.com.adrianorodrigues.controleacoes.service.CarteiraService;
import br.com.adrianorodrigues.controleacoes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    CarteiraService carteiraService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("{id}/carteira")
    public CarteiraDTO getCarteira(Pageable pageable, @PathVariable("id") Long id) {
        return carteiraService.getCarteira(id);
    }

    @GetMapping("{id}")
    public UsuarioDTO get(@PathVariable("id") Long id) {
        return usuarioService.getUser(id);
    }

    @PostMapping(consumes = "application/json")
    public UsuarioDTO post(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.saveUser(usuarioDTO);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public UsuarioDTO put(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(id);
        return usuarioService.saveUser(usuarioDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        usuarioService.delete(id);
    }
}
