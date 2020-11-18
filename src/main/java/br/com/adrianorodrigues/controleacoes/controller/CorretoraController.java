package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.CorretoraDTO;
import br.com.adrianorodrigues.controleacoes.mapper.CorretoraDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.CorretoraMapper;
import br.com.adrianorodrigues.controleacoes.service.CorretoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "corretora")
public class CorretoraController {
    @Autowired
    CorretoraService corretoraService;

    @GetMapping
    public Set<CorretoraDTO> get() {
        return corretoraService.find().stream()
                .map((corretora -> CorretoraDtoMapper.from(corretora).map()))
                .collect(Collectors.toSet());
    }
    
    @GetMapping("{id}")
    public CorretoraDTO get(@PathVariable("id") Long id) {
        return CorretoraDtoMapper.from(corretoraService.find(id)).map();
    }

    @PostMapping(consumes = "application/json")
    public CorretoraDTO post(@Valid @RequestBody CorretoraDTO corretoraDTO) {
        return CorretoraDtoMapper.from(corretoraService.save(CorretoraMapper.from(corretoraDTO).map())).map();
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public CorretoraDTO put(@PathVariable Long id, @Valid @RequestBody CorretoraDTO corretoraDTO) {
        corretoraDTO.setId(id);
        return CorretoraDtoMapper.from(corretoraService.save(CorretoraMapper.from(corretoraDTO).map())).map();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        corretoraService.delete(id);
    }
}
