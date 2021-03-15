package com.alejandroquindimil.proyectorecpm.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alejandroquindimil.proyectorecpm.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        initButtons(v);
        initData(v);
        return v;}

    private void initButtons(View v) {
    }

    private void initData(View v) {
        RecyclerView contenedor =v.findViewById(R.id.frg_search_recyclerview);
        Activity ctx = getActivity();

        List<Aplicacion> aplicacion = new ArrayList<>();


        for (int idx = 0; idx < 100; idx++) {
            aplicacion.add(new Aplicacion(1+idx));
        }

        final AplicacionAdapterListener listener = new AplicacionAdapterListener() {
            @Override
            public void click(Aplicacion item, int position) {
                //
                aplicacion.clear();

                for (int idx = 0; idx < 100; idx++) {
                    aplicacion.add(new Aplicacion(1+idx, "Aplicacion"));
                }

                AplicacionAdapter adaptador = new AplicacionAdapter(ctx, aplicacion,  null);
                contenedor.setAdapter(adaptador);

            }
        };

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        LinearLayoutManager manager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridManager = new GridLayoutManager(ctx, 1);


        contenedor.setLayoutManager(gridManager);

        AplicacionAdapter adaptador = new AplicacionAdapter(ctx, aplicacion, listener);
        contenedor.setAdapter(adaptador);


    }
}
