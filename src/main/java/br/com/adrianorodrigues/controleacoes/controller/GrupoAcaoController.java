package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.GrupoAcaoDto;
import br.com.adrianorodrigues.controleacoes.mapper.GrupoAcaoDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.GrupoAcaoMapper;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.service.GrupoAcaoService;
import br.com.adrianorodrigues.controleacoes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "grupo-acao")
public class GrupoAcaoController {
    @Autowired
    GrupoAcaoService grupoAcaoService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<GrupoAcaoDto> get(@RequestParam("idUsuario") Long idUsuario) {
        return grupoAcaoService.findByIdUsuario(idUsuario).stream()
                .map((grupoAcao -> GrupoAcaoDtoMapper.from(grupoAcao).map()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public GrupoAcaoDto getById(@PathVariable("id") Long id) {
        return GrupoAcaoDtoMapper.from(grupoAcaoService.find(id)).map();
    }

    @PostMapping(consumes = "application/json")
    public GrupoAcaoDto post(@Valid @RequestBody GrupoAcaoDto grupoAcaoDto) {
        Usuario usuario = usuarioService.findUsuarioById(grupoAcaoDto.getIdUsuario());
        return GrupoAcaoDtoMapper.from(grupoAcaoService.save(GrupoAcaoMapper.from(grupoAcaoDto, usuario).map())).map();
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public GrupoAcaoDto put(@PathVariable Long id, @Valid @RequestBody GrupoAcaoDto grupoAcaoDto) {
        grupoAcaoDto.setId(id);
        Usuario usuario = usuarioService.findUsuarioById(grupoAcaoDto.getIdUsuario());
        return GrupoAcaoDtoMapper.from(grupoAcaoService.save(GrupoAcaoMapper.from(grupoAcaoDto, usuario).map())).map();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        grupoAcaoService.delete(id);
    }
}
