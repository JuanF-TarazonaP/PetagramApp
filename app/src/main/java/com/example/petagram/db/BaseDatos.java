package com.example.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

import static com.example.petagram.db.ConstantesBaseDatos.*;

/**
 * Base de datos SQLite con la tabla "mascota", idéntica al POJO Mascota.
 * Solo guarda las últimas 5 mascotas que recibieron raiting.
 */
public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + TABLE_MASCOTA + " ("
                + TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY, "
                + TABLE_MASCOTA_NOMBRE + " TEXT NOT NULL, "
                + TABLE_MASCOTA_FOTO + " TEXT NOT NULL, "
                + TABLE_MASCOTA_RAITING + " INTEGER NOT NULL DEFAULT 0, "
                + TABLE_MASCOTA_ULTIMO_RAITING + " INTEGER NOT NULL"
                + ")";
        db.execSQL(queryCrearTablaMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTA);
        onCreate(db);
    }

    /**
     * Guarda los datos completos de la mascota que recibió raiting.
     * Si ya existía (mismo id) se actualiza; luego se borran las que
     * no estén entre las últimas 5 con raiting.
     */
    public void guardarMascotaConRaiting(Mascota mascota) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues valores = new ContentValues();
            valores.put(TABLE_MASCOTA_ID, mascota.getId());
            valores.put(TABLE_MASCOTA_NOMBRE, mascota.getNombre());
            valores.put(TABLE_MASCOTA_FOTO, mascota.getFoto());
            valores.put(TABLE_MASCOTA_RAITING, mascota.getRaiting());
            valores.put(TABLE_MASCOTA_ULTIMO_RAITING, mascota.getUltimoRaiting());
            db.insertWithOnConflict(TABLE_MASCOTA, null, valores, SQLiteDatabase.CONFLICT_REPLACE);

            db.execSQL("DELETE FROM " + TABLE_MASCOTA
                    + " WHERE " + TABLE_MASCOTA_ID + " NOT IN ("
                    + "SELECT " + TABLE_MASCOTA_ID + " FROM " + TABLE_MASCOTA
                    + " ORDER BY " + TABLE_MASCOTA_ULTIMO_RAITING + " DESC"
                    + " LIMIT " + MAX_FAVORITAS + ")");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /** Devuelve las últimas 5 mascotas con raiting, de la más reciente a la más antigua. */
    public ArrayList<Mascota> obtenerUltimasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MASCOTA
                + " ORDER BY " + TABLE_MASCOTA_ULTIMO_RAITING + " DESC"
                + " LIMIT " + MAX_FAVORITAS, null);

        while (cursor.moveToNext()) {
            mascotas.add(leerMascota(cursor));
        }
        cursor.close();
        db.close();
        return mascotas;
    }

    /** Busca una mascota por id; devuelve null si no está guardada. */
    public Mascota obtenerMascota(int id) {
        Mascota mascota = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MASCOTA
                + " WHERE " + TABLE_MASCOTA_ID + " = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            mascota = leerMascota(cursor);
        }
        cursor.close();
        db.close();
        return mascota;
    }

    private Mascota leerMascota(Cursor c) {
        Mascota m = new Mascota();
        m.setId(c.getInt(c.getColumnIndexOrThrow(TABLE_MASCOTA_ID)));
        m.setNombre(c.getString(c.getColumnIndexOrThrow(TABLE_MASCOTA_NOMBRE)));
        m.setFoto(c.getString(c.getColumnIndexOrThrow(TABLE_MASCOTA_FOTO)));
        m.setRaiting(c.getInt(c.getColumnIndexOrThrow(TABLE_MASCOTA_RAITING)));
        m.setUltimoRaiting(c.getLong(c.getColumnIndexOrThrow(TABLE_MASCOTA_ULTIMO_RAITING)));
        return m;
    }
}
