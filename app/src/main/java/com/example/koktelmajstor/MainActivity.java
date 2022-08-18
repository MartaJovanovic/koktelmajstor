package com.example.koktelmajstor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView slika;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slika = findViewById(R.id.logo);
        slika.setImageResource(R.drawable.logo);

    }


    public void prelazakNaRecp(View view) {
        Intent i=new Intent(this, Recepti.class);
        i.putExtra("omiljeni", false);
        startActivity(i);
    }

    public void prelazakNaOmilj(View view) {
        Intent i=new Intent(this, Recepti.class);
        i.putExtra("omiljeni", true);
        startActivity(i);
    }


    public void prelazakNaIgr(View view) {
        Intent i=new Intent(this, Igre.class);
        startActivity(i);




    }

}