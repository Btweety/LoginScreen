package com.example.android.loginscreen.adapters.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.loginscreen.R;
import com.example.android.loginscreen.database.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;
    private int rowLayout;
    private Context context;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout aproveLayout;
        TextView nome;
        TextView turno;
        TextView data;
        TextView preferencia;

        public UserViewHolder(View v) {
            super(v);
            aproveLayout = v.findViewById(R.id.layout_aprovacao_card);
            nome = v.findViewById(R.id.tv_nome_foto);
            turno = v.findViewById(R.id.turno_aprove);
            data = v.findViewById(R.id.data_aprove);
            preferencia = v.findViewById(R.id.preferencia_aprove);
        }
    }

    public UserAdapter(List<User> users, int rowLayout, Context context) {
        this.users = users;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        try{
            holder.nome.setText(users.get(position).getName());
            holder.nome.setText(users.get(position).getName());
            /** TODO: holder.turno.setText(users.get(position).getTurno()); */
            /** TODO: holder.data.setText(users.get(position).getData()); */
            holder.preferencia.setText(users.get(position).getPreferencia());
        }catch (NullPointerException e){
            Log.d("ERRO API", e.getMessage());
        }


    }
    @Override
    public int getItemCount() {
        return users.size();
    }


}