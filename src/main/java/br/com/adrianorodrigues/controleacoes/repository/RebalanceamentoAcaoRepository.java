package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.RebalanceamentoAcao;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RebalanceamentoAcaoRepository extends JpaRepository<RebalanceamentoAcao, Long> {
    List<RebalanceamentoAcao> findAllByUsuario(Usuario usuario);
}