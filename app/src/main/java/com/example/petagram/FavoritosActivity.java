package com.example.petagram;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.adapter.MascotaAdaptador;
import com.example.petagram.db.BaseDatos;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Muestra las últimas 5 mascotas que recibieron raiting,
 * leídas de la base de datos SQLite.
 */
public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView rvFavoritos;
    private TextView tvVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Botón "subir" (flecha) para regresar al Activity padre
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.titulo_favoritos);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvFavoritos = findViewById(R.id.rvFavoritos);
        tvVacio = findViewById(R.id.tvVacio);
        rvFavoritos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Mascota> favoritas = new BaseDatos(this).obtenerUltimasMascotas();
        rvFavoritos.setAdapter(new MascotaAdaptador(favoritas, false));
        tvVacio.setVisibility(favoritas.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
