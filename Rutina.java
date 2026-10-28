package com.mycompany.infinityforce;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private int id;
    private int idSocio;
    private String nombre;
    private String objetivo;
    private List<String> ejercicios;

    public Rutina(int id, int idSocio,
                  String nombre, String objetivo) {
        this.id = id;
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(String ejercicio) {
        if (ejercicio != null && !ejercicio.isBlank()) {
            ejercicios.add(ejercicio.trim());
        }
    }

    public List<String> getEjercicios() {
        return new ArrayList<>(ejercicios);
    }

    public String obtenerDetalle() {
        return "Rutina: " + nombre
                + "\nObjetivo: " + objetivo
                + "\nEjercicios: "
                + String.join(", ", ejercicios);
    }

    public int getId() {
        return id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }
}
