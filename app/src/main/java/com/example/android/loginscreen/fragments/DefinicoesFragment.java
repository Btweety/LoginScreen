package com.example.android.loginscreen.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
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
    private CardView cv_saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definicoes, container, false);
        initialize(view);

        try {
            Intent intent = getActivity().getIntent();
            user = (User) intent.getSerializableExtra("user");
            Log.e("Definicoes Sem Erro", user.getName());
        }catch (NullPointerException e){
            Log.e("Definicoes Erro", e.getMessage());
        }

        et_nome_perfil.setText(user.getName());
        et_email_perfil.setText(user.getEmail());
        et_tlm_perfil.setText(user.getTelnum());
        cv_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(et_nome_perfil.getText().toString());
                user.setEmail(et_email_perfil.getText().toString());
                user.setTelnum(et_tlm_perfil.getText().toString());
                /** ToDo: update user na BD */
            }
        });
        return view;
    }

    public void initialize(View view) {
        et_nome_perfil = view.findViewById(R.id.et_nome_perfil);
        et_email_perfil = view.findViewById(R.id.et_email_perfil);
        et_tlm_perfil = view.findViewById(R.id.et_tlm_perfil);
        ofertaTrocaTurno = view.findViewById(R.id.switch_oferta_turno);
        getOfertaTrocaTurnoEmail = view.findViewById(R.id.switch_oferta_email);
        cv_saveBtn = view.findViewById(R.id.cv_savebtn);
    }

}
