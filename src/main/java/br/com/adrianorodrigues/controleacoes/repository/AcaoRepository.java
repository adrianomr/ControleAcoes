package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoRepository extends JpaRepository<Acao, Long> {
    List<Acao> findByCodigoBdiInAndTipoMercado(List<String> codigoBdi, Integer tipoMercado);

    Acao findOneByPapel(String papel);

    Page<Acao> findAllByPapelContaining(String papel, Pageable pageable);
}