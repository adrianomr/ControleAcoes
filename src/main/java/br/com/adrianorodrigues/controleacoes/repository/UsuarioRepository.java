package br.com.adrianorodrigues.controleacoes.repository;

import br.com.adrianorodrigues.controleacoes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}