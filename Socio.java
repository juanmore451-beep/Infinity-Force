
package com.mycompany.infinityforce;


import java.time.LocalDate;

public class Socio {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private LocalDate fechaInscripcion;

    public Socio(int id, String nombre, String correo,
                 String telefono, LocalDate fechaInscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }
}

