package br.com.adrianorodrigues.controleacoes.dto;

public class AcaoDTO {
    private Long id;
    private String papel;
    private Double valor;
    private Double precoMedio;
    private Long quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(Double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
