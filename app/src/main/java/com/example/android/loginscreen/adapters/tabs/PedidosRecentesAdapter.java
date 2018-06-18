package com.example.android.loginscreen.adapters.tabs;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
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

public class PedidosRecentesAdapter extends RecyclerView.Adapter<com.example.android.loginscreen.adapters.tabs.PedidosRecentesAdapter.RecenteViewHolder> {
    private List<Aprove> pedidosRecentes;
    private int rowLayout;
    private Context context;

    /** Obter uma ligaçao directa para cada elemento do Layout **/
    public static class RecenteViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout aproveLayout;
        CircleImageView foto;
        TextView nome;
        TextView turno;
        TextView data;
        TextView preferencia;
        CardView cardAceitar;
        TextView aceitaRejeita;

        /** construtor que afeta cada variável com o valor da seu layout **/
        public RecenteViewHolder(View v) {
            super(v);
            aproveLayout = v.findViewById(R.id.layout_pedidos_recentes);
            foto = v.findViewById(R.id.im_foto_recente);
            nome = v.findViewById(R.id.tv_nome_foto_recente);
            turno = v.findViewById(R.id.turno_recente);
            data = v.findViewById(R.id.data_recente);
            preferencia = v.findViewById(R.id.preferencia_recente);
            cardAceitar = v.findViewById(R.id.card_recente);
            aceitaRejeita = v.findViewById(R.id.tv_isAproved);
        }
    }

    public PedidosRecentesAdapter(List<Aprove> pedidosRecentes, int rowLayout, Context context) {
        this.pedidosRecentes = pedidosRecentes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    /**  "enche" o layout e retorna o holder **/
    @Override
    public com.example.android.loginscreen.adapters.tabs.PedidosRecentesAdapter.RecenteViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new com.example.android.loginscreen.adapters.tabs.PedidosRecentesAdapter.RecenteViewHolder(view);
    }

    /** definir os atributos do layout com base na informação obtida **/
    @Override
    public void onBindViewHolder(com.example.android.loginscreen.adapters.tabs.PedidosRecentesAdapter.RecenteViewHolder holder, final int position) {
        Picasso.with(context).load(pedidosRecentes.get(position).getUserFoto()).fit().centerCrop().into(holder.foto);
        holder.nome.setText(pedidosRecentes.get(position).getUserName());
        holder.turno.setText(pedidosRecentes.get(position).getTurno());
        holder.data.setText(pedidosRecentes.get(position).getData());
        holder.preferencia.setText(pedidosRecentes.get(position).getPreferencia());

        if(pedidosRecentes.get(position).isAproved()){
            holder.cardAceitar.setCardBackgroundColor(context.getResources().getColor(R.color.cardAceitar));
            holder.aceitaRejeita.setText(R.string.aprovado);
        }
        else{
            holder.cardAceitar.setCardBackgroundColor(context.getResources().getColor(R.color.cardRejeitar));
            holder.aceitaRejeita.setText(R.string.rejeitado);
        }
    }

    /** numero de items**/
    @Override
    public int getItemCount() {
        return pedidosRecentes.size();
    }
}