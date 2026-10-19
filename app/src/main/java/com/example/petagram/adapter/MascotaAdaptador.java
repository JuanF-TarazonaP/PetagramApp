package com.example.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Adapter: infla y recicla el layout de cada ítem y contiene el ViewHolder.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private final ArrayList<Mascota> mascotas;
    private final boolean permitirRaiting;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, boolean permitirRaiting) {
        this.mascotas = mascotas;
        this.permitirRaiting = permitirRaiting;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRaiting.setText(String.valueOf(mascota.getRaiting()));

        if (permitirRaiting) {
            holder.btnLike.setVisibility(View.VISIBLE);
            holder.btnLike.setOnClickListener(v -> {
                mascota.darRaiting();
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    notifyItemChanged(pos);
                }
                Toast.makeText(v.getContext(),
                        v.getContext().getString(R.string.toast_like, mascota.getNombre()),
                        Toast.LENGTH_SHORT).show();
            });
        } else {
            holder.btnLike.setVisibility(View.GONE);
            holder.btnLike.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    /**
     * ViewHolder: clase anidada que guarda las referencias a los views de cada ítem.
     */
    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        final ImageView imgFoto;
        final ImageButton btnLike;
        final TextView tvNombre;
        final TextView tvRaiting;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            btnLike = itemView.findViewById(R.id.btnLike);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRaiting = itemView.findViewById(R.id.tvRaiting);
        }
    }
}
