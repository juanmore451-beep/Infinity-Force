
package com.mycompany.infinityforce;

import java.time.LocalDate;

public class Membresia {
    private int id;
    private int idSocio;
    private String tipo;
    private double precio;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;

    public Membresia(int id, int idSocio, String tipo,
                     double precio, LocalDate fechaInicio,
                     LocalDate fechaVencimiento) {
        this.id = id;
        this.idSocio = idSocio;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio)
                && !hoy.isAfter(fechaVencimiento);
    }

    public String getEstado() {
        return estaVigente() ? "Vigente" : "Vencida";
    }

    public int getId() {
        return id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
}
