package com.example.petagram.pojo;

/**
 * DataSet: entidad POJO que representa a una mascota.
 */
public class Mascota {

    private String nombre;
    private int foto;      // recurso drawable de la foto
    private int raiting;   // cantidad de huesos recibidos

    public Mascota(String nombre, int foto, int raiting) {
        this.nombre = nombre;
        this.foto = foto;
        this.raiting = raiting;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    /** Suma un hueso al raiting de la mascota. */
    public void darRaiting() {
        raiting++;
    }
}
