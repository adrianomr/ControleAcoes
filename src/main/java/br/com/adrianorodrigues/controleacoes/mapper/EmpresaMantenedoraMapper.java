package br.com.adrianorodrigues.controleacoes.mapper;

import br.com.adrianorodrigues.controleacoes.dto.EmpresaMantenedoraDTO;
import br.com.adrianorodrigues.controleacoes.model.Acao;
import br.com.adrianorodrigues.controleacoes.model.EmpresaMantenedora;
import lombok.Builder;

@Builder
public class EmpresaMantenedoraMapper {
    EmpresaMantenedora empresaMantenedora;
    public static EmpresaMantenedoraMapper from(EmpresaMantenedoraDTO empresaMantenedoraDTO, Acao acao){
        EmpresaMantenedora empresaMantenedoraFromDTO = EmpresaMantenedora
                .builder()
                .id(empresaMantenedoraDTO.getId())
                .descricao(empresaMantenedoraDTO.getDescricao())
                .cnpj(empresaMantenedoraDTO.getCnpj())
                .acao(acao)
                .build();
        return EmpresaMantenedoraMapper
                .builder()
                .empresaMantenedora(empresaMantenedoraFromDTO)
                .build();
    }
    public EmpresaMantenedora map(){
        return empresaMantenedora;
    }
}
