package com.example.petagram.pojo;

/**
 * DataSet: entidad POJO que representa a una mascota.
 * La tabla "mascota" de la base de datos tiene exactamente estos mismos campos.
 */
public class Mascota {

    private int id;              // identificador único de la mascota
    private String nombre;
    private String foto;         // nombre del recurso drawable, ej. "mascota_1"
    private int raiting;         // cantidad de huesos recibidos
    private long ultimoRaiting;  // momento (millis) del último raiting recibido

    public Mascota() {
    }

    public Mascota(int id, String nombre, String foto, int raiting) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.raiting = raiting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public long getUltimoRaiting() {
        return ultimoRaiting;
    }

    public void setUltimoRaiting(long ultimoRaiting) {
        this.ultimoRaiting = ultimoRaiting;
    }

    /** Suma un hueso al raiting y registra el momento en que se dio. */
    public void darRaiting() {
        raiting++;
        ultimoRaiting = System.currentTimeMillis();
    }
}
