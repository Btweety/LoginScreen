package com.example.android.loginscreen.adapters.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    private List<User> aproves;
    private int rowLayout;
    private Context context;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout aproveLayout;
        CircleImageView foto;
        TextView nome;
        TextView turno;
        TextView data;
        TextView preferencia;

        public UserViewHolder(View v) {
            super(v);
            aproveLayout = v.findViewById(R.id.layout_aprovacao_card);
            foto = v.findViewById(R.id.im_foto);
            nome = v.findViewById(R.id.tv_nome_foto);
            turno = v.findViewById(R.id.turno_aprove);
            data = v.findViewById(R.id.data_aprove);
            preferencia = v.findViewById(R.id.preferencia_aprove);
        }
    }

    public UserAdapter(List<User> aproves, int rowLayout, Context context) {
        this.aproves = aproves;
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
        Picasso.with(context).load(R.drawable.add_button).fit().centerCrop().into(holder.foto);
        holder.nome.setText(aproves.get(position).getName());
        holder.turno.setText("turno");
        holder.data.setText("data");
        holder.preferencia.setText("data");

    }
    @Override
    public int getItemCount() {
        return aproves.size();
    }


}