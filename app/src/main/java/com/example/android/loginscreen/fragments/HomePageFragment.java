package com.example.android.loginscreen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.loginscreen.R;
import com.example.android.loginscreen.adapters.home.AprovesAdapter;
import com.example.android.loginscreen.adapters.home.UserAdapter;
import com.example.android.loginscreen.database.models.Empresa;
import com.example.android.loginscreen.database.models.History;
import com.example.android.loginscreen.database.models.Schedule;
import com.example.android.loginscreen.database.models.User;
import com.example.android.loginscreen.database.rest.APIClient;
import com.example.android.loginscreen.database.rest.APIInterface;
import com.example.android.loginscreen.models.Aprove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> users = new ArrayList<>();
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_home_page);

        /** Dar um tipo de layout à recyclerview */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        /** Criar dados de teste para povoar a recyclerview a partir do seu adapter */
        final ArrayList<Aprove> listaDeAproves = new ArrayList<Aprove>();
        /*
        for (int i = 0; i < 6; i++) {
            String userFoto = "https://images.unsplash.com/photo-1520272820796-02e71f701951?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9810af68e5144e9a7ad27f6298e86587&auto=format&fit=crop&w=634&q=80";
            String userName = "Pedro António " + i;
            String turno = "Turno da manhã " + i;
            String data = "Terça-feira, 26 de Maio " + i;
            String preferencia = "Preferência: Tarde " + i;
            listaDeAproves.add(new Aprove(userFoto, userName, turno, data, preferencia));
        }
        */
        final String userFoto = "https://images.unsplash.com/photo-1520272820796-02e71f701951?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9810af68e5144e9a7ad27f6298e86587&auto=format&fit=crop&w=634&q=80";
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<User> call = apiInterface.getUserByEmail("henrique@email.com");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                /*  User user = response.body(); */
                ArrayList<Schedule> schedules = new ArrayList();
                ArrayList<Empresa> empresas = new ArrayList();
                ArrayList<History> histories = new ArrayList();
                user = new User(
                        response.body().getId(),
                        response.body().getName(),
                        response.body().getEmail(),
                        response.body().getTelnum(),
                        schedules,
                        empresas,
                        response.body().getPreferencia(),
                        histories);

                Log.d("API", response.body().getName());
                Log.d("API", response.body().getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

        for (int i = 0; i < 10; i++) {
            users.add(user);
        }

        /** Povoar a recyclerview */
        // recyclerView.setAdapter(new UserAdapter(users, R.layout.espera_aprovacao_card, getContext()));
        return view;
    }
}
