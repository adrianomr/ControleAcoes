package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Subscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscricaoRepository extends JpaRepository<Subscricao, Long> {
    List<Subscricao> findAllByUsuarioId(Long idUsuario);
}