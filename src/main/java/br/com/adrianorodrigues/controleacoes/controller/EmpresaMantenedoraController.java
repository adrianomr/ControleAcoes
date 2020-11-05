package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.service.EmpresaMantenedoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "empresa-controladora")
public class EmpresaMantenedoraController {
    @Autowired
    EmpresaMantenedoraService empresaMantenedoraService;

    @GetMapping
    public Set<EmpresaMantenedoraDTO> get(@RequestParam("idAcao") Long idAcao) {
        return empresaMantenedoraService.getEmpresaControladora(idAcao);
    }

    @PostMapping(consumes = "application/json")
    public EmpresaMantenedoraDTO post(@Valid @RequestBody EmpresaMantenedoraDTO empresaMantenedoraDTO) {
        return empresaMantenedoraService.save(empresaMantenedoraDTO);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public EmpresaMantenedoraDTO put(@PathVariable Long id, @Valid @RequestBody EmpresaMantenedoraDTO empresaMantenedoraDTO) {
        empresaMantenedoraDTO.setId(id);
        return empresaMantenedoraService.save(empresaMantenedoraDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        empresaMantenedoraService.delete(id);
    }
}
