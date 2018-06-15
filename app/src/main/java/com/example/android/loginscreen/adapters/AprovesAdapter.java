package com.example.android.loginscreen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.loginscreen.R;
import com.example.android.loginscreen.models.Aprove;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AprovesAdapter extends RecyclerView.Adapter<AprovesAdapter.AproveViewHolder> {
    private List<Aprove> aproves;
    private int rowLayout;
    private Context context;

    public static class AproveViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout aproveLayout;
        CircleImageView foto;
        TextView nome;
        TextView turno;
        TextView data;
        TextView preferencia;

        public AproveViewHolder(View v) {
            super(v);
            aproveLayout = v.findViewById(R.id.layout_aprovacao_card);
            foto = v.findViewById(R.id.im_foto);
            nome = v.findViewById(R.id.tv_nome_foto);
            turno = v.findViewById(R.id.turno_aprove);
            data = v.findViewById(R.id.data_aprove);
            preferencia = v.findViewById(R.id.preferencia_aprove);
        }

    }

    public AprovesAdapter(List<Aprove> aproves, int rowLayout, Context context) {
        this.aproves = aproves;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public AprovesAdapter.AproveViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AproveViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AproveViewHolder holder, final int position) {

        Picasso.with(context).load(aproves.get(position).getUserFoto()).fit().centerCrop().into(holder.foto);
        holder.nome.setText(aproves.get(position).getUserName());
        holder.turno.setText(aproves.get(position).getTurno());
        holder.data.setText(aproves.get(position).getData());
        holder.preferencia.setText(aproves.get(position).getPreferencia());

    }


    @Override
    public int getItemCount() {
        return aproves.size();
    }


}
