package br.com.adrianorodrigues.ControleAcoes.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "acao")
public class Acao {
    @Id
    @GeneratedValue
    private long id;
    @Column(columnDefinition = "varchar(100)")
    private String papel;
    @Column(columnDefinition = "varchar(100)")
    private String nomeEmpresa;
    @Column(columnDefinition = "numeric(20)")
    private int quantidade;
    @Column(columnDefinition = "numeric(20,4)")
    private BigDecimal valor;

    public Acao(int id, String papel, String nomeEmpresa, int quantidade, BigDecimal valor) {
        this.id = id;
        this.papel = papel;
        this.nomeEmpresa = nomeEmpresa;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Acao(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Acao{" +
                "id=" + id +
                ", papel='" + papel + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }
}
