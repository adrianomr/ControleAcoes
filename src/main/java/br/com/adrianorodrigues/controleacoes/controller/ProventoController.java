package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.ProventoDTO;
import br.com.adrianorodrigues.controleacoes.service.provento.ProventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping(value = "provento")
public class ProventoController {
    @Autowired
    ProventoService proventoService;

    @GetMapping
    public Set<ProventoDTO> get(@RequestParam("idAcao") Long idAcao) {
        return proventoService.getProventoSetByAcao(idAcao);
    }

    @PostMapping(consumes = "application/json")
    public ProventoDTO post(@Valid @RequestBody ProventoDTO proventoDTO) {
        return proventoService.save(proventoDTO);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public ProventoDTO put(@PathVariable Long id, @Valid @RequestBody ProventoDTO proventoDTO) {
        proventoDTO.setId(id);
        return proventoService.save(proventoDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        proventoService.delete(id);
    }

    @PostMapping("/excel")
    public void importAcaoList(MultipartFile file) throws IOException {
        proventoService.importProventoList(file);
    }
}
