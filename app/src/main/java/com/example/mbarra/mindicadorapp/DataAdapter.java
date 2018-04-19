package com.example.mbarra.mindicadorapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Indicador> mIndicadores;

    public DataAdapter(ArrayList<Indicador> indicadores) {
        this.mIndicadores = indicadores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Indicador indicador = mIndicadores.get(position);
        holder.txtNombre.setText(indicador.getNombre());
        @SuppressWarnings("unchecked")
        ArrayList<Serie> arrayList = (ArrayList<Serie>)(ArrayList<?>)indicador.getSerie();
        Serie today = arrayList.get(0);
        Serie yesterday = arrayList.get(1);
        StringBuilder valor = new StringBuilder();
        String todayValue = Utility.formatCurrency(today.getValor());
        if (indicador.getUnidad_medida().equalsIgnoreCase("pesos")) {
            valor.append(todayValue)
                    .append(" ")
                    .append(Utility.PESO);
        } else {
            valor.append(todayValue)
                    .append(" ")
                    .append(Utility.DOLAR);
        }
        holder.txtValor.setText(valor.toString());
        BigDecimal difference = Utility.difference(today.getValor(), yesterday.getValor());
        if (difference.compareTo(BigDecimal.ZERO) > 0) {
            holder.txtInfo.setTextColor(Color.parseColor("#009688"));
        } else {
            holder.txtInfo.setTextColor(Color.parseColor("#F44336"));
        }
        holder.txtInfo.setText(Utility.formatCurrency(difference));
    }

    @Override
    public int getItemCount() {
        return mIndicadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtValor;
        private TextView txtInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtInfo = itemView.findViewById(R.id.txtInfo);
        }
    }
}
