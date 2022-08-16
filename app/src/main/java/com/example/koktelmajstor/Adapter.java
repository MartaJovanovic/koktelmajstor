package com.example.koktelmajstor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Kartica> lista;
    MOnClickListener aOnClickListener;


    public Adapter(ArrayList<Kartica> lista,MOnClickListener aOnClickListener){
        this.lista = lista;
        this.aOnClickListener=aOnClickListener;
    }

    // U ovoj metodi se inflatuje layout koji prikazuje jednu stavku liste
    // i prosledjuje viewHolderu nasu listu
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.kartice,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem, aOnClickListener);
        return viewHolder;
    }


    // u ovoj metodi postavljamo podatke, konkretne vrednosti, stavke nase listu na UI
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // U KOLIKO NEKOME ISKOCI GRESKA DA NE MOZE DA SE PROSLEDI POZICIJA JER JE DEPRICATED
        // SVUDA UMESTO NJE PROSLEDITE holder.getAdapterPosition();

        Kartica kartica = lista.get(position);
        holder.naslov.setText(kartica.getNaziv());
        holder.recept.setText(kartica.getRecept());
//        holder.imageView.setImageResource(R.drawable.crno);

    }

    // vraca duzinu liste
    @Override
    public int getItemCount() {
        return lista.size();
    }

    // ViewHolder klasa sluzi za instanciranje UI stavke
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView naslov;
        TextView recept;
        ImageView imageView;
        MOnClickListener mOnClickListener;

        public ViewHolder(@NonNull View itemView, MOnClickListener mOnClickListener) {
            super(itemView);
            naslov = itemView.findViewById(R.id.naslov);
            recept = itemView.findViewById(R.id.recept);
            itemView.setOnClickListener(this);
            this.mOnClickListener=mOnClickListener;
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(getAdapterPosition());

        }
    }

    public interface MOnClickListener{
        void onClick(int pozicija);
    }
}
