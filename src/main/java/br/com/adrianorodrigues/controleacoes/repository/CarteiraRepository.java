package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Set<Carteira> findAllByUsuarioId(Long idUsuario);

    Optional<Carteira> findByCorretoraIdAndUsuarioId(Long idCorretora, Long idUsuario);
}