package br.com.adrianorodrigues.ControleAcoes.dto;

public class CotacoesBovespaDto {
    //N(2) 1 - 2
    private int tipoRegistro;
    //N(8) 3 - 10
    private String data;
    //X(2) 11 -12
    private String codigoBdi;
    //X(12) 13 - 24
    private String codNegociacaoPapel;
    //N(3) 25 - 27
    private int tipoMercado;
    //X(12)
    private String nomeResumidoEmpresaEmissora;
    //X(10)
    private String especificacaoPapel;
    //X(03)
    private String prazoMercadoTermo;
    //X(04)
    private String moedaReferencia;
    //N(12)
    private double precoAbertura;
    //N(12)
    private double precoMaximo;
    //N(12)
    private double precoMinimo;
    //N(12)
    private double precoFechamento;
    //N(12)
    private double precoMedio;
    //N(12)
    private double precoUltimoNegocio;
    //N(12)
    private double precoMelhorOfertaCompra;
    //N(12)
    private double precoMelhorOfertaVenda;
    //N(5)
    private int numeroNegociosEfetuados;
    //N(18)
    private long quantidadeTitulosNegociados;
    //N(11)
    private long volumeTotalTitulos;
    //X(12)
    private String codigoPapel;

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public CotacoesBovespaDto setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
        return this;
    }

    public String getData() {
        return data;
    }

    public CotacoesBovespaDto setData(String data) {
        this.data = data;
        return this;
    }

    public String getCodigoBdi() {
        return codigoBdi;
    }

    public CotacoesBovespaDto setCodigoBdi(String codigoBdi) {
        this.codigoBdi = codigoBdi;
        return this;
    }

    public String getCodNegociacaoPapel() {
        return codNegociacaoPapel;
    }

    public CotacoesBovespaDto setCodNegociacaoPapel(String codNegociacaoPapel) {
        this.codNegociacaoPapel = codNegociacaoPapel;
        return this;
    }

    public int getTipoMercado() {
        return tipoMercado;
    }

    public CotacoesBovespaDto setTipoMercado(int tipoMercado) {
        this.tipoMercado = tipoMercado;
        return this;
    }

    public String getNomeResumidoEmpresaEmissora() {
        return nomeResumidoEmpresaEmissora;
    }

    public CotacoesBovespaDto setNomeResumidoEmpresaEmissora(String nomeResumidoEmpresaEmissora) {
        this.nomeResumidoEmpresaEmissora = nomeResumidoEmpresaEmissora;
        return this;
    }

    public String getEspecificacaoPapel() {
        return especificacaoPapel;
    }

    public CotacoesBovespaDto setEspecificacaoPapel(String especificacaoPapel) {
        this.especificacaoPapel = especificacaoPapel;
        return this;
    }

    public String getPrazoMercadoTermo() {
        return prazoMercadoTermo;
    }

    public CotacoesBovespaDto setPrazoMercadoTermo(String prazoMercadoTermo) {
        this.prazoMercadoTermo = prazoMercadoTermo;
        return this;
    }

    public String getMoedaReferencia() {
        return moedaReferencia;
    }

    public CotacoesBovespaDto setMoedaReferencia(String moedaReferencia) {
        this.moedaReferencia = moedaReferencia;
        return this;
    }

    public double getPrecoAbertura() {
        return precoAbertura;
    }

    public CotacoesBovespaDto setPrecoAbertura(double precoAbertura) {
        this.precoAbertura = precoAbertura;
        return this;
    }

    public double getPrecoMaximo() {
        return precoMaximo;
    }

    public CotacoesBovespaDto setPrecoMaximo(double precoMaximo) {
        this.precoMaximo = precoMaximo;
        return this;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public CotacoesBovespaDto setPrecoMinimo(double precoMinimo) {
        this.precoMinimo = precoMinimo;
        return this;
    }

    public double getPrecoFechamento() {
        return precoFechamento;
    }

    public CotacoesBovespaDto setPrecoFechamento(double precoFechamento) {
        this.precoFechamento = precoFechamento;
        return this;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public CotacoesBovespaDto setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
        return this;
    }

    public double getPrecoUltimoNegocio() {
        return precoUltimoNegocio;
    }

    public CotacoesBovespaDto setPrecoUltimoNegocio(double precoUltimoNegocio) {
        this.precoUltimoNegocio = precoUltimoNegocio;
        return this;
    }

    public double getPrecoMelhorOfertaCompra() {
        return precoMelhorOfertaCompra;
    }

    public CotacoesBovespaDto setPrecoMelhorOfertaCompra(double precoMelhorOfertaCompra) {
        this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
        return this;
    }

    public double getPrecoMelhorOfertaVenda() {
        return precoMelhorOfertaVenda;
    }

    public CotacoesBovespaDto setPrecoMelhorOfertaVenda(double precoMelhorOfertaVenda) {
        this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
        return this;
    }

    public int getNumeroNegociosEfetuados() {
        return numeroNegociosEfetuados;
    }

    public CotacoesBovespaDto setNumeroNegociosEfetuados(int numeroNegociosEfetuados) {
        this.numeroNegociosEfetuados = numeroNegociosEfetuados;
        return this;
    }

    public long getQuantidadeTitulosNegociados() {
        return quantidadeTitulosNegociados;
    }

    public CotacoesBovespaDto setQuantidadeTitulosNegociados(long quantidadeTitulosNegociados) {
        this.quantidadeTitulosNegociados = quantidadeTitulosNegociados;
        return this;
    }

    public long getVolumeTotalTitulos() {
        return volumeTotalTitulos;
    }

    public CotacoesBovespaDto setVolumeTotalTitulos(long volumeTotalTitulos) {
        this.volumeTotalTitulos = volumeTotalTitulos;
        return this;
    }

    public String getCodigoPapel() {
        return codigoPapel;
    }

    public CotacoesBovespaDto setCodigoPapel(String codigoPapel) {
        this.codigoPapel = codigoPapel;
        return this;
    }
}
