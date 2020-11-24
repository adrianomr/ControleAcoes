package br.com.adrianorodrigues.controleacoes.service;

import br.com.adrianorodrigues.controleacoes.dto.UsuarioDTO;
import br.com.adrianorodrigues.controleacoes.exception.ResourceNotFoundException;
import br.com.adrianorodrigues.controleacoes.mapper.UsuarioDtoMapper;
import br.com.adrianorodrigues.controleacoes.mapper.UsuarioMapper;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import br.com.adrianorodrigues.controleacoes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioDTO getUser(Long id) {
        Usuario usuario = findUsuarioById(id);
        return UsuarioDtoMapper.from(usuario).map();
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
    }

    public UsuarioDTO saveUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.save(UsuarioMapper.from(usuarioDTO).map());
        return UsuarioDtoMapper.from(usuario).map();
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
