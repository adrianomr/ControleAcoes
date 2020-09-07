package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.RebalanceamentoAcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RebalanceamentoAcaoService {
    @Autowired
    RebalanceamentoAcaoRepository rebalanceamentoAcaoRepository;

    public void save(RebalanceamentoAcao rebalanceamentoAcao) {
        rebalanceamentoAcaoRepository.save(rebalanceamentoAcao);
    }

    public List<RebalanceamentoAcao> findAllByUsuario(Long idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        return rebalanceamentoAcaoRepository.findAllByUsuario(usuario);
    }

}
