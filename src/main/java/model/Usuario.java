package model;

import java.util.HashMap;

public class Usuario {
    private int id;
    private String nome;
    private Carteira carteira;

    public Usuario(int id, String nome, Carteira carteira) {
        this.id = id;
        this.nome = nome;
        this.carteira = carteira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", carteira=" + carteira +
                '}';
    }
}
