package com.example.android.loginscreen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.loginscreen.R;
import com.example.android.loginscreen.adapters.AprovesAdapter;
import com.example.android.loginscreen.models.Aprove;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_home_page);

        /** Dar um tipo de layout à recyclerview */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);

        /** Criar dados de teste para povoar a recyclerview a partir do seu adapter */
        ArrayList<Aprove> listaDeAproves = new ArrayList<Aprove>();
        for (int i = 0; i < 6; i++){
            String userFoto = "https://images.unsplash.com/photo-1520272820796-02e71f701951?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9810af68e5144e9a7ad27f6298e86587&auto=format&fit=crop&w=634&q=80";
            String userName = "Pedro " + i;
            String turno = "Turno da manhã " + i;
            String data = "26 de Maio " + i;
            String preferencia = "Tarde " + i;
            listaDeAproves.add(new Aprove(userFoto, userName, turno, data, preferencia));
        }

        /** Povoar a recyclerview */
        recyclerView.setAdapter(new AprovesAdapter(listaDeAproves, R.layout.espera_aprovacao_card, getContext()));
        return view;
    }
}
