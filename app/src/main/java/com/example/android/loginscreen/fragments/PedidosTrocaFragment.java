package com.example.android.loginscreen.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.loginscreen.AdicionarTrocaActivity;
import com.example.android.loginscreen.R;
import com.example.android.loginscreen.adapters.home.AprovesAdapter;
import com.example.android.loginscreen.adapters.tabs.PedidosRecentesAdapter;
import com.example.android.loginscreen.adapters.tabs.TrocasDisponiveisAdapter;
import com.example.android.loginscreen.database.models.History;
import com.example.android.loginscreen.database.models.User;
import com.example.android.loginscreen.models.Aprove;

import java.util.ArrayList;

public class PedidosTrocaFragment extends Fragment {

    private User user;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private ArrayList<Aprove> trocasdisponiveis = new ArrayList<Aprove>();
    private ArrayList<Aprove> aespera = new ArrayList<Aprove>();
    private ArrayList<Aprove> seuspedidos = new ArrayList<Aprove>();
    private ArrayList<Aprove> pedidosrecentes = new ArrayList<Aprove>();
    private FloatingActionButton myFAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedidos_troca, container, false);

        recyclerView = view.findViewById(R.id.pedidos_recycler);
        tabLayout = view.findViewById(R.id.tablayout_pedidos);
        myFAB = view.findViewById(R.id.myFAB);

        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AdicionarTrocaActivity.class));
            }
        });

        /** Get User data*/
        try {
            Intent intent = getActivity().getIntent();
            user = (User) intent.getSerializableExtra("user");
            Log.e("Trocas Frag Sem Erro", user.getName());
        } catch (NullPointerException e) {
            Log.e("Trocas Frag Erro", e.getMessage());
        }

        /** Dar um tipo de layout à recyclerview */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        /** Criar dados de teste para povoar a recyclerview a partir do seu adapter */
        final ArrayList<Aprove> listaDeAproves = new ArrayList<Aprove>();
        final ArrayList<Aprove> listaDeAprovesEspera = new ArrayList<Aprove>();
        for (int i = 0; i < 6; i++) {
            String userFoto = "https://images.unsplash.com/photo-1520272820796-02e71f701951?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9810af68e5144e9a7ad27f6298e86587&auto=format&fit=crop&w=634&q=80";
            String userName = "Pedro " + i;
            String turno = "Turno da manhã " + i;
            String data = "26 de Maio " + i;
            String preferencia = "Tarde " + i;
            boolean isAproved = false;
            if (i % 2 == 0)
                isAproved = true;
            listaDeAproves.add(new Aprove(userFoto, userName, turno, data, preferencia));
            listaDeAprovesEspera.add(new Aprove(userFoto, userName, turno, data, preferencia, isAproved));
        }
        recyclerView.setAdapter(new TrocasDisponiveisAdapter(listaDeAproves, R.layout.troca_disponivel_card, getContext()));

        //getTrocasDisponiveis();

        /** Selecionar que layout carregar com base no menu escolhido **/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        recyclerView.setAdapter(new TrocasDisponiveisAdapter(listaDeAproves, R.layout.troca_disponivel_card, getContext()));
                        break;
                    case 1:
                        recyclerView.setAdapter(new AprovesAdapter(listaDeAproves, R.layout.espera_aprovacao_card, getContext()));
                        break;
                    case 2:
                        recyclerView.setAdapter(new AprovesAdapter(listaDeAproves, R.layout.espera_aprovacao_card, getContext()));
                        break;
                    case 3:
                        recyclerView.setAdapter(new PedidosRecentesAdapter(listaDeAprovesEspera, R.layout.pedidos_recentes_card, getContext()));
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }

    private void getTrocasDisponiveis() {
        String date;
        String hora = "cdcd";
        String[] dateTime;

        String userFoto = "https://images.unsplash.com/photo-1520272820796-02e71f701951?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9810af68e5144e9a7ad27f6298e86587&auto=format&fit=crop&w=634&q=80";
        for (History history : user.getHistory()) {
            /** obter a data (dia/mes/ano) e hora(hora:minuto:segundo) em separado */
            Log.e("data", history.getNewschedule());
            date = history.getNewschedule().split(" ")[0];
            //hora = history.getNewschedule().split(" ")[1];
            //hora = hora.substring(0, 2);

            String status = history.getStatus();
            Aprove aprove = new Aprove(
                    userFoto,
                    history.getIduserchange(),
                    hora,
                    date,
                    user.getPreferencia()
            );

            /** Se o pedido já foi aprovado/recusado */
            if (status.equals("aprovado") || status.equals("rejeitado")) {
                aprove.setAproved((status.equals("aprovado")));
                pedidosrecentes.add(aprove);
            }

            /** Se o pedido já ainda está pendente (pedidos feitos e pedidos que precisam de resposta */
            if (status.equals("pendente")) {
                /** pedidos feitos */
                if (history.isUserrequested()) {
                    seuspedidos.add(aprove);
                }
                /** ou pedidos que necessitam de resposta */
                else {
                    aespera.add(aprove);
                }
            }
            /** pedidos de trocas */
            if (status.equals("espera")) {
                trocasdisponiveis.add(aprove);
            }
        }
    }
}
