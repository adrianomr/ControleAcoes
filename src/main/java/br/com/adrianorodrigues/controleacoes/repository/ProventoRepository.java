package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.Provento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProventoRepository extends JpaRepository<Provento, Long> {

    Set<Provento> findAllByAcao(Acao acao);
}