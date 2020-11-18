package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.UsuarioDTO;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.Builder;

@Builder
public class UsuarioMapper {
    Usuario usuario;

    public static UsuarioMapper from(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario
                .builder()
                .id(usuarioDTO.getId())
                .nome(usuarioDTO.getNome())
                .username(usuarioDTO.getUsername())
                .build();
        return UsuarioMapper
                .builder()
                .usuario(usuario)
                .build();
    }

    public Usuario map() {
        return usuario;
    }
}
