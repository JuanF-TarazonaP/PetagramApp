package com.example.petagram.db;

/**
 * Nombres de la base de datos, la tabla y sus columnas.
 */
public final class ConstantesBaseDatos {

    private ConstantesBaseDatos() {
    }

    public static final String DATABASE_NAME = "petagram.db";
    public static final int DATABASE_VERSION = 1;

    /** Cantidad máxima de mascotas que se guardan (las últimas con raiting). */
    public static final int MAX_FAVORITAS = 5;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";
    public static final String TABLE_MASCOTA_FOTO = "foto";
    public static final String TABLE_MASCOTA_RAITING = "raiting";
    public static final String TABLE_MASCOTA_ULTIMO_RAITING = "ultimo_raiting";
}
