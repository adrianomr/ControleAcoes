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
                .id(empresaMantenedora.getId())
                .descricao(empresaMantenedora.getDescricao())
                .cnpj(empresaMantenedora.getCnpj())
                .idAcao(empresaMantenedora.getAcao().getId())
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
