package com.mycompany.infinityforce;

public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String categoria,
                    double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    public boolean vender(int cantidad) {
        if (!hayStock(cantidad)) {
            return false;
        }

        stock -= cantidad;
        return true;
    }

    public double calcularTotal(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero.");
        }

        return precio * cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}


