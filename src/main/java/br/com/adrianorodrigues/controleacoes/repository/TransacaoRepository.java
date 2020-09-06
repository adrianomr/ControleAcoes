package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findAllByUsuario(Usuario usuario);
}