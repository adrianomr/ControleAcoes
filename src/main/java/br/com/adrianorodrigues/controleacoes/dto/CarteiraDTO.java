package br.com.adrianorodrigues.controleacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteiraDTO {
    private Double valorInvestido;
    private Double valorAtual;
    private Double lucroPrejuizo;
    private List<AcaoDTO> acoes;

    public Double getValorInvestido() {
        return valorInvestido == null ? 0 : valorInvestido;
    }

    public void setValorInvestido(Double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public Double getValorAtual() {
        return valorAtual == null ? 0 : valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getLucroPrejuizo() {
        return lucroPrejuizo == null ? 0 : lucroPrejuizo;
    }

    public void setLucroPrejuizo(Double lucroPrejuizo) {
        this.lucroPrejuizo = lucroPrejuizo;
    }

    public List<AcaoDTO> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<AcaoDTO> acoes) {
        this.acoes = acoes;
    }
}
