
package com.mycompany.infinityforce;

import java.time.LocalDateTime;

public class Asistencia {
    private int id;
    private int idSocio;
    private LocalDateTime fechaHora;

    public Asistencia(int id, int idSocio,
                      LocalDateTime fechaHora) {
        this.id = id;
        this.idSocio = idSocio;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String obtenerDetalle() {
        return "Socio ID: " + idSocio
                + " | Fecha y hora: " + fechaHora;
    }
}
