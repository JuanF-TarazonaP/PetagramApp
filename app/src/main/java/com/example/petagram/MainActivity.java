package com.example.petagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.adapter.MascotaAdaptador;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMascotas = findViewById(R.id.rvMascotas);

        // LayoutManager: da forma y acomoda los ítems en una lista vertical
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        inicializarMascotas();
        rvMascotas.setAdapter(new MascotaAdaptador(mascotas, true));
    }

    /** DataSet de la lista principal. */
    private void inicializarMascotas() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Rocky", R.drawable.mascota_1, 3));
        mascotas.add(new Mascota("Luna", R.drawable.mascota_2, 5));
        mascotas.add(new Mascota("Max", R.drawable.mascota_3, 2));
        mascotas.add(new Mascota("Kira", R.drawable.mascota_4, 4));
        mascotas.add(new Mascota("Toby", R.drawable.mascota_5, 1));
        mascotas.add(new Mascota("Nala", R.drawable.mascota_6, 6));
        mascotas.add(new Mascota("Simba", R.drawable.mascota_7, 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favoritos) {
            startActivity(new Intent(this, FavoritosActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
