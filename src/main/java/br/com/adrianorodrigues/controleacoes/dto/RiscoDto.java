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
public class RiscoDto {
    private Double totalRiscoBaixo = 0d;
    private Double totalRiscoMedio = 0d;
    private Double totalRiscoAlto = 0d;
    private Double valorTotal = 0d;
    private List<AcaoDTO> acaoList;

    public double getRiscoAltoPercentual() {
        return valorTotal > 0 ? totalRiscoAlto / valorTotal : 0;
    }

    public double getRiscoMedioPercentual() {
        return valorTotal > 0 ? totalRiscoMedio / valorTotal : 0;
    }

    public double getRiscoBaixoPercentual() {
        return valorTotal > 0 ? totalRiscoBaixo / valorTotal : 0;
    }
}
