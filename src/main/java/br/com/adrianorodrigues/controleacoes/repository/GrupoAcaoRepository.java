package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.GrupoAcao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoAcaoRepository extends JpaRepository<GrupoAcao, Long> {
    List<GrupoAcao> findAllByUsuarioId(Long idUsuario);
}