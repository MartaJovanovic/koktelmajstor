package com.example.koktelmajstor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Kartica> lista;
    MOnClickListener aOnClickListener;
    private Context mContext;


    public Adapter(ArrayList<Kartica> lista,MOnClickListener aOnClickListener, Context mContext){
        this.lista = lista;
        this.aOnClickListener=aOnClickListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.kartice,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem, aOnClickListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Kartica kartica = lista.get(position);
        holder.naslov.setText(kartica.getNaziv());
        holder.recept.setText(kartica.getRecept());

        if (mContext instanceof Recepti) {
            try {
                holder.imageView.setImageDrawable(((Recepti)mContext).ucitajSliku(kartica.getSlika()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

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
            imageView = itemView.findViewById(R.id.slika);
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
