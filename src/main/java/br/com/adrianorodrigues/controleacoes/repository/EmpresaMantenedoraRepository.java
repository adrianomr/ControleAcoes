package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.EmpresaMantenedora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EmpresaMantenedoraRepository extends JpaRepository<EmpresaMantenedora, Long> {
    Set<EmpresaMantenedora> findAllByAcao(Acao acao);
}