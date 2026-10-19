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
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Fragment con la lista de mascotas (lo que antes estaba en MainActivity).
 */
public class RecyclerViewFragment extends Fragment {

    private ArrayList<Mascota> mascotas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarMascotas();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        RecyclerView rvMascotas = v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
        rvMascotas.setAdapter(new MascotaAdaptador(mascotas, true));

        return v;
    }

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
}
