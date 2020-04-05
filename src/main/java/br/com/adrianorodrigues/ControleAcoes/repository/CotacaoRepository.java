package br.com.adrianorodrigues.ControleAcoes.repository;

import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}