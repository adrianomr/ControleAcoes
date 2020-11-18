package br.com.adrianorodrigues.controleacoes.service.transacao;

import br.com.adrianorodrigues.controleacoes.dto.TransacaoDTO;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Carteira;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.TipoTransacao;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.TransacaoRepository;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import br.com.adrianorodrigues.controleacoes.service.CarteiraService;
import br.com.adrianorodrigues.controleacoes.service.acao.AcaoService;
import br.com.adrianorodrigues.controleacoes.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TransacaoService {
    @Autowired
    AcaoService acaoService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    CarteiraService carteiraService;

    private Acao findOrCreateAcao(String papel) {
        Acao acao = acaoService.findAcaoByPapel(papel);
        if (acao == null) {
            acao = new Acao();
            acao.setPapel(papel);
            acaoService.insertAcao(acao);
        }
        return acao;
    }

    public void saveTrasacao(TransacaoDTO transacaoDTO, TipoTransacao tipoTransacao) {
        Acao acao = findOrCreateAcao(transacaoDTO.getPapel());
        Usuario usuario = usuarioRepository
                .findById(transacaoDTO.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não existe"));
        Carteira carteira = carteiraService.findCarteiraById(transacaoDTO.getIdCarteira());
        Transacao transacao = new Transacao();
        transacao.setData(transacaoDTO.getData());
        transacao.setAcao(acao);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setQuantidade(transacaoDTO.getQuantidade());
        transacao.setValor(BigDecimal.valueOf(transacaoDTO.getValor()));
        transacao.setUsuario(usuario);
        transacao.setCarteira(carteira);
        transacaoRepository.save(transacao);
    }

    public List<TransacaoDTO> findAllTransacaoes() {
        return transacaoRepository.findAll().stream().map(transacao -> {
            int fator = transacao.getTipoTransacao().equals(TipoTransacao.COMPRA) ? 1 : -1;
            return TransacaoDTO
                    .builder()
                    .id(transacao.getId())
                    .papel(transacao.getAcao().getPapel())
                    .valor(fator * transacao.getValor().doubleValue())
                    .idUsuario(transacao.getUsuario().getId())
                    .quantidade(transacao.getQuantidade())
                    .data(transacao.getData())
                    .build();
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Transacao transacao = new Transacao();
        transacao.setId(id);
        transacaoRepository.delete(transacao);
    }

    public void importTransacaoList(MultipartFile file, Long idUsuario, Long idCorretora) throws IOException {
        Sheet sheet = ExcelUtil.getSheet(file);
        Carteira carteira = carteiraService.findCarteiraByCorretoraAndUsuario(idCorretora, idUsuario);
        ExcelUtil.processRows(sheet, new ImportTransacaoService(this, idUsuario, carteira.getId()), 11);
    }
}
