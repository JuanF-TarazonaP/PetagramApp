package com.example.petagram;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.adapter.MascotaAdaptador;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Pantalla que en el futuro guardará las últimas 5 mascotas con raiting.
 * Por ahora muestra 5 mascotas "dummy" (hardcodeadas).
 */
public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Botón "subir" (flecha) para regresar al Activity padre
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.titulo_favoritos);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<Mascota> favoritas = new ArrayList<>();
        favoritas.add(new Mascota("Nala", R.drawable.mascota_6, 6));
        favoritas.add(new Mascota("Luna", R.drawable.mascota_2, 5));
        favoritas.add(new Mascota("Kira", R.drawable.mascota_4, 4));
        favoritas.add(new Mascota("Rocky", R.drawable.mascota_1, 3));
        favoritas.add(new Mascota("Max", R.drawable.mascota_3, 2));

        RecyclerView rvFavoritos = findViewById(R.id.rvFavoritos);
        rvFavoritos.setLayoutManager(new LinearLayoutManager(this));
        rvFavoritos.setAdapter(new MascotaAdaptador(favoritas, false));
    }
}
