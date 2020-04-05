package br.com.adrianorodrigues.ControleAcoes.repository;

import br.com.adrianorodrigues.ControleAcoes.model.Acao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcaoRepository extends JpaRepository<Acao, Long> {

}