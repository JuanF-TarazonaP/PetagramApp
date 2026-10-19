package com.example.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Adapter del Grid de fotos del perfil: cada ítem muestra la foto
 * y la cantidad de raits (huesos) que ha recibido.
 */
public class FotoAdaptador extends RecyclerView.Adapter<FotoAdaptador.FotoViewHolder> {

    private final ArrayList<Mascota> fotos;

    public FotoAdaptador(ArrayList<Mascota> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_foto, parent, false);
        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        Mascota foto = fotos.get(position);
        holder.imgFoto.setImageResource(foto.getFoto());
        holder.tvRaiting.setText(String.valueOf(foto.getRaiting()));
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgFoto;
        final TextView tvRaiting;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoGrid);
            tvRaiting = itemView.findViewById(R.id.tvRaitingGrid);
        }
    }
}
