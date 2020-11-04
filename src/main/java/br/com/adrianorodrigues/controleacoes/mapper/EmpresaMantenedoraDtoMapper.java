package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.model.EmpresaMantenedora;
import lombok.Builder;
@Builder
public class EmpresaMantenedoraDtoMapper {
    EmpresaMantenedoraDTO empresaMantenedoraDTO;
    public static EmpresaMantenedoraDtoMapper from(EmpresaMantenedora empresaMantenedora){
        EmpresaMantenedoraDTO empresaMantenedoraDTO = EmpresaMantenedoraDTO
                .builder()
                .descricao(empresaMantenedora.getDescricao())
                .build();
        return EmpresaMantenedoraDtoMapper
                .builder()
                .empresaMantenedoraDTO(empresaMantenedoraDTO)
                .build();
    }
    public EmpresaMantenedoraDTO map(){
        return empresaMantenedoraDTO;
    }
}
