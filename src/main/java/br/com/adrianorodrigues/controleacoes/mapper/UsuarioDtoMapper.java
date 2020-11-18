package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.UsuarioDTO;
import br.com.adrianorodrigues.controleacoes.model.Usuario;
import lombok.Builder;

@Builder
public class UsuarioDtoMapper {
    UsuarioDTO usuarioDTO;

    public static UsuarioDtoMapper from(Usuario usuario) {
        UsuarioDTO usuarioDTO = UsuarioDTO
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .username(usuario.getUsername())
                .build();
        return UsuarioDtoMapper
                .builder()
                .usuarioDTO(usuarioDTO)
                .build();
    }

    public UsuarioDTO map() {
        return usuarioDTO;
    }
}
