package com.mycompany.infinityforce;

import java.time.LocalDate;

public class Pago {
    private int id;
    private int idSocio;
    private double monto;
    private String concepto;
    private LocalDate fecha;
    private String metodoPago;

    public Pago(int id, int idSocio, double monto,
                String concepto, LocalDate fecha,
                String metodoPago) {
        if (monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero.");
        }

        this.id = id;
        this.idSocio = idSocio;
        this.monto = monto;
        this.concepto = concepto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }

    public int getId() {
        return id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public double getMonto() {
        return monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
}

