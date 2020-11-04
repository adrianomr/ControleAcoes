package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.service.EmpresaMantenedoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("empresa-controladora")
public class EmpresaMantenedoraController {
    @Autowired
    EmpresaMantenedoraService empresaMantenedoraService;

    @GetMapping()
    public Set<EmpresaMantenedoraDTO> getContas(@RequestParam("idAcao") Long idAcao) {
        return empresaMantenedoraService.getEmpresaControladora(idAcao);
    }

    @PostMapping(consumes = "application/json")
    public EmpresaMantenedoraDTO postRebalanceamento(@Valid @RequestBody EmpresaMantenedoraDTO empresaMantenedoraDTO) {
        return empresaMantenedoraService.save(empresaMantenedoraDTO);
    }
}
