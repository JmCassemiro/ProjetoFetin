package com.example.crud_operations.model;

public class TipoProduto {
    private final String nome;
    private final int icone;

    public TipoProduto(String nome, int icone) {
        this.nome = nome;
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    public int getIcone() {
        return icone;
    }
}
