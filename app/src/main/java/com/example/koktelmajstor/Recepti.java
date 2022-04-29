package com.example.koktelmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Recepti extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recepti_activity);
    }


    public void sortirajPoVrsti(View view) {
    }


    public void sortirajPoJacini(View view) {
    }

    public void nazad(View view) {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
