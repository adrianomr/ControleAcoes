package br.com.adrianorodrigues.ControleAcoes.repository;

import br.com.adrianorodrigues.ControleAcoes.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    @Query(value = "select * from cotacao where acao_id = :idAcao order by data limit 1", nativeQuery = true)
    Cotacao getCotacaoAtual(@Param("idAcao") Long idAcao);
}