package br.com.adrianorodrigues.controleacoes.controller;

import br.com.adrianorodrigues.controleacoes.dto.SubscricaoDTO;
import br.com.adrianorodrigues.controleacoes.mapper.SubscricaoDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.SubscricaoMapper;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import br.com.adrianorodrigues.controleacoes.service.CarteiraService;
import br.com.adrianorodrigues.controleacoes.service.SubscricaoService;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("subscricao")
public class SubscricaoController {

    @Autowired
    SubscricaoService subscricaoService;
    @Autowired
    AcaoService acaoService;
    @Autowired
    CarteiraService carteiraService;

    @GetMapping("")
    public List<SubscricaoDTO> getCarteira(Pageable pageable, @RequestParam("idUsuario") Long idUsuario) {
        return subscricaoService
                .findAllByUsuario(idUsuario)
                .stream()
                .map(subscricao -> SubscricaoDtoMapper.from(subscricao).map())
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public SubscricaoDTO get(@PathVariable("id") Long id) {
        return SubscricaoDtoMapper.from(subscricaoService.find(id)).map();
    }

    @PostMapping(consumes = "application/json")
    public SubscricaoDTO post(@Valid @RequestBody SubscricaoDTO subscricaoDTO) {
        Acao acao = acaoService.findAcaoByPapel(subscricaoDTO.getPapel());
        Carteira carteira = carteiraService.findCarteiraById(subscricaoDTO.getIdCarteira());
        Subscricao subscricao = subscricaoService.save(SubscricaoMapper.from(subscricaoDTO, acao, carteira).map());
        return SubscricaoDtoMapper.from(subscricao).map();
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public SubscricaoDTO put(@PathVariable Long id, @Valid @RequestBody SubscricaoDTO subscricaoDTO) {
        subscricaoDTO.setId(id);
        Acao acao = acaoService.findAcaoByPapel(subscricaoDTO.getPapel());
        Carteira carteira = carteiraService.findCarteiraById(subscricaoDTO.getIdCarteira());
        Subscricao subscricao = subscricaoService.save(SubscricaoMapper.from(subscricaoDTO, acao, carteira).map());
        return SubscricaoDtoMapper.from(subscricao).map();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        subscricaoService.delete(id);
    }
}
