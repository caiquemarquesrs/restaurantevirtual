package com.example.restaurantevirtual;

import java.awt.*;

public class ItemPedido {

    private MenuItem item;
    private int quantidade;

    public ItemPedido(MenuItem item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return item.getPreco() * quantidade;
    }
}
