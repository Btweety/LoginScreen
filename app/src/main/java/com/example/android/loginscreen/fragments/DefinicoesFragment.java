package com.example.android.loginscreen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.loginscreen.R;
import com.example.android.loginscreen.database.models.User;

public class DefinicoesFragment extends Fragment {

    private User user;
    private EditText et_nome_perfil, et_email_perfil, et_tlm_perfil;
    private SwitchCompat ofertaTrocaTurno, getOfertaTrocaTurnoEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definicoes, container, false);

        Bundle bundle = new Bundle();
        user = (User) bundle.getSerializable("user");

        et_nome_perfil.setText(user.getName());
        et_email_perfil.setText(user.getEmail());
        et_tlm_perfil.setText(user.getTelnum());

        return view;
    }

}
