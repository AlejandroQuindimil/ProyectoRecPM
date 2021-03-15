package com.alejandroquindimil.proyectorecpm.listeners;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.modelos.Aplicacion;

import java.util.ArrayList;
import java.util.List;

public class AplicacionAdapter extends RecyclerView.Adapter<AplicacionAdapter.AplicacionHolder> {

    private static final int layout = R.layout.aplicacion_adapter_item;
    private final AplicacionAdapterListener listener;
    private final Activity act;

    private List<Aplicacion> items= new ArrayList<Aplicacion>();

    public AplicacionAdapter(Activity act,List<Aplicacion> items, AplicacionAdapterListener listener) {
        this.act = act;
        this.items= items;
        this.listener= listener;
    }

    @NonNull
    @Override
    public AplicacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new AplicacionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AplicacionHolder holder, int position) {
        final Aplicacion item = items.get(position);

        String texto = item.getName();
        holder.txt.setText(texto);

        holder.linRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.click(item, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
       return items.size();
    }

    public class AplicacionHolder extends RecyclerView.ViewHolder {
       public TextView txt;
       public ImageView icon;
       public LinearLayout Lnlay;
       public ConstraintLayout linRoot;

        void initView(View v){
            txt = v.findViewById(R.id.aplicacion_adapter_txt);
            icon = v.findViewById(R.id.aplicacion_adapter_img);
            Lnlay= v.findViewById(R.id.aplicacion_lnlyaout_H);
            linRoot = v.findViewById(R.id.aplicacion_adapter_root);
        }

        public AplicacionHolder(View v) {
            super(v);
            initView(v);
        }

    }
}