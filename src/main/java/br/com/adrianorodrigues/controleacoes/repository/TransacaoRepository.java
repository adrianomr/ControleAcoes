package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.model.transacao.Transacao;
import br.com.adrianorodrigues.controleacoes.repository.view.AcaoDtoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findAllByUsuario(Usuario usuario);

    @Query("select " +
            "transacao.acao.id as id, " +
            "transacao.acao.papel as papel, " +
            "SUM(case transacao.tipoTransacao when 0 then transacao.quantidade else -transacao.quantidade end) as quantidade " +
            "from Transacao transacao " +
            "group by " +
            "transacao.acao.id, " +
            "transacao.acao.papel ")
    List<AcaoDtoView> findAllDistinctAcaoByUsuarioIdGroupByAcao(Long usuarioId);
}