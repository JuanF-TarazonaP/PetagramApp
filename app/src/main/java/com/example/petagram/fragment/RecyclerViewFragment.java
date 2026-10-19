package com.example.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.MascotaAdaptador;
import com.example.petagram.db.BaseDatos;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Fragment con la lista de mascotas.
 */
public class RecyclerViewFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        rvMascotas = v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializarMascotas();
        rvMascotas.setAdapter(new MascotaAdaptador(mascotas, true));
    }

    private void inicializarMascotas() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(1, "Rocky", "mascota_1", 3));
        mascotas.add(new Mascota(2, "Luna", "mascota_2", 5));
        mascotas.add(new Mascota(3, "Max", "mascota_3", 2));
        mascotas.add(new Mascota(4, "Kira", "mascota_4", 4));
        mascotas.add(new Mascota(5, "Toby", "mascota_5", 1));
        mascotas.add(new Mascota(6, "Nala", "mascota_6", 6));
        mascotas.add(new Mascota(7, "Simba", "mascota_7", 0));

        // Si la mascota ya está guardada, se usa el raiting de la base de datos
        BaseDatos db = new BaseDatos(requireContext());
        for (Mascota m : mascotas) {
            Mascota guardada = db.obtenerMascota(m.getId());
            if (guardada != null) {
                m.setRaiting(guardada.getRaiting());
                m.setUltimoRaiting(guardada.getUltimoRaiting());
            }
        }
    }
}
