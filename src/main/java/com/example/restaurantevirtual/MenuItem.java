package com.example.restaurantevirtual;

public class MenuItem {

    private int id;
    private String nome;
    private double preco;
    private String tipo;
    private String imagePath;

    public MenuItem(int id, String nome, double preco, String tipo, String imagePath) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImagePath() {
        return imagePath;
    }

}
