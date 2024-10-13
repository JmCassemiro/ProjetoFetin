package com.example.crud_operations.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Produtos implements Parcelable {

    private Long id;
    private String nome;
    private String marca;
    private double preco;
    private String localizacao;
    private String tipo;
    private int quantidade; // Novo campo para quantidade

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produtos() {
    }

    protected Produtos(Parcel in) {
        id = in.readLong();
        nome = in.readString();
        marca = in.readString();
        preco = in.readDouble();
        localizacao = in.readString();
        tipo = in.readString(); // Ler tipo do Parcel
        quantidade = in.readInt(); // Ler quantidade do Parcel
    }

    public static final Creator<Produtos> CREATOR = new Creator<Produtos>() {
        @Override
        public Produtos createFromParcel(Parcel in) {
            return new Produtos(in);
        }

        @Override
        public Produtos[] newArray(int size) {
            return new Produtos[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nome);
        dest.writeString(marca);
        dest.writeDouble(preco);
        dest.writeString(localizacao);
        dest.writeString(tipo);
        dest.writeInt(quantidade);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
