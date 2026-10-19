package com.example.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapter.FotoAdaptador;
import com.example.petagram.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Fragment con el perfil de la mascota: foto redonda, nombre y
 * un RecyclerView en forma de Grid con todas sus fotos (datos dummy).
 */
public class PerfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        CircularImageView imgPerfil = v.findViewById(R.id.imgPerfil);
        TextView tvNombre = v.findViewById(R.id.tvNombrePerfil);
        imgPerfil.setImageResource(R.drawable.mascota_6);
        tvNombre.setText("Nala");

        RecyclerView rvFotos = v.findViewById(R.id.rvFotos);
        rvFotos.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvFotos.setAdapter(new FotoAdaptador(crearFotosDummy()));

        return v;
    }

    private ArrayList<Mascota> crearFotosDummy() {
        String[] fotos = {
                "mascota_6", "mascota_1", "mascota_2",
                "mascota_3", "mascota_4", "mascota_5",
                "mascota_7", "mascota_6", "mascota_2",
                "mascota_4", "mascota_1", "mascota_3"
        };
        int[] raits = {8, 5, 3, 7, 2, 9, 4, 6, 1, 3, 5, 2};

        ArrayList<Mascota> lista = new ArrayList<>();
        for (int i = 0; i < fotos.length; i++) {
            lista.add(new Mascota(100 + i, "Nala", fotos[i], raits[i]));
        }
        return lista;
    }
}
