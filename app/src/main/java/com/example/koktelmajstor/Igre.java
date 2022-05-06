package com.example.koktelmajstor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

public class Igre extends AppCompatActivity {
    ImageView karte;
    int [] slike= {
            R.drawable.detelina2,R.drawable.detelina3,
            R.drawable.detelina4,R.drawable.detelina5,
            R.drawable.detelina6,R.drawable.detelina7,
            R.drawable.detelina8,R.drawable.detelina9,
            R.drawable.detelina10,R.drawable.detelinaj,
            R.drawable.detelinaq,R.drawable.detelinak,
            R.drawable.list2,R.drawable.list3,
            R.drawable.list4,R.drawable.list5,
            R.drawable.list6,R.drawable.list7,
            R.drawable.list8,R.drawable.list9,
            R.drawable.list10,R.drawable.listj,
            R.drawable.listq,R.drawable.listk,
            R.drawable.srce2,R.drawable.srce3,
            R.drawable.srce4,R.drawable.srce5,
            R.drawable.srce6,R.drawable.srce7,
            R.drawable.srce8,R.drawable.srce9,
            R.drawable.srce10,R.drawable.srcej,
            R.drawable.srceq,R.drawable.srcek,
            R.drawable.kocka2,R.drawable.kocka3,
            R.drawable.kocka4,R.drawable.kocka5,
            R.drawable.kocka6,R.drawable.kocka7,
            R.drawable.kocka8,R.drawable.kocka9,
            R.drawable.kocka10,R.drawable.kockaj,
            R.drawable.kockaq,R.drawable.kockak,
            R.drawable.detelinaa, R.drawable.lista,
            R.drawable.srcea,R.drawable.kockaa};
    int broj=0;
    ImageView pravila;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.igre_activity);
        karte = findViewById(R.id.karte);
        shuffle(slike);
        karte.setImageResource(slike[0]);
        pravila = findViewById(R.id.pravila);
        pravila.setImageResource(R.drawable.crno);

    }

    public static void shuffle(int br[])
    {
        for (int i = br.length - 1; i >= 1; i--)
        {
            Random rand = new Random();
            int j = rand.nextInt(i + 1);
            zamena(br, i, j);
        }
    }
    private static void zamena(int[] br, int i, int j) {
        int k = br[i];
        br[i] = br[j];
        br[j] = k;
    }

    public void dalje(View view){
        broj++;
        if (broj >= slike.length) {
            broj = 0;
        }
        karte.setImageResource(slike[broj]);
    }

    public void josIgara(View view){
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( "https://drinkinggamezone.com/card-drinking-games/" ) );
        startActivity( browse );
    }

    public void nazad(View view) {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
