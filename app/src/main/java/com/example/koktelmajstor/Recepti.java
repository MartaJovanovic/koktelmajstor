package com.example.koktelmajstor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;

import java.util.ArrayList;

public class Recepti extends AppCompatActivity implements Adapter.MOnClickListener {

    ArrayList<Kartica> kartice;
    Adapter adapter;
    RecyclerView recyclerView;
    RadioGroup rg;
    RadioButton sve;
    RadioButton slabo;
    RadioButton srednje;
    RadioButton jako;
    CheckBox tekila;
    CheckBox vodka;
    CheckBox rum;
    CheckBox dzin;
    MyDBHelperOmiljeni dbHelper;
    boolean omiljeni;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.recepti_activity);


        rg = findViewById(R.id.rg);
        sve = findViewById(R.id.sve);
        slabo = findViewById(R.id.slabo);
        srednje = findViewById(R.id.srednje);
        jako = findViewById(R.id.jako);
        tekila = findViewById(R.id.tekila);
        vodka = findViewById(R.id.vodka);
        rum = findViewById(R.id.rum);
        dzin = findViewById(R.id.dzin);
        recyclerView = findViewById(R.id.listaRecepata);


        dbHelper = new MyDBHelperOmiljeni(this);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                filter(kartice);
            }
        });

        tekila.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  filter(kartice);
                                              }
                                          }
        );
        vodka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                 filter(kartice);
                                             }
                                         }
        );
        rum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                           @Override
                                           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                               filter(kartice);
                                           }
                                       }
        );
        dzin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                filter(kartice);
                                            }
                                        }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();

        omiljeni = (boolean) getIntent().getSerializableExtra("omiljeni");
        if (!omiljeni) kartice = vratiSve();
        else kartice = vratiOmiljene();
        filter(kartice);

    }


    public void filter(ArrayList<Kartica> k) {
        ArrayList<Kartica> nova = new ArrayList<>();
        String[] uslovi = uslovi();
        String j = jacina();
        Log.i("JACINA", "> JACINA: " + j + "\n");
        for (int i = 0; i < k.size(); i++) {
            String[] tagovi = k.get(i).getTagovi().split(",");
            if ((sadrzi(tagovi, uslovi)) && ima(tagovi, j)) nova.add(k.get(i));
        }

        adapter = new Adapter(nova, this, this);
        recyclerView.setAdapter(adapter);
    }

    public boolean sadrzi(String[] prvi, String[] drugi) {
        if (drugi[0].equals("")) return true;
        for (int i = 0; i < prvi.length; i++) {
            for (int j = 0; j < drugi.length; j++) {
                if (prvi[i].equals(drugi[j])) return true;
            }
        }
        return false;
    }

    public boolean ima(String[] tagovi, String ja) {
        if (ja.equals("sve")) return true;
        for (int i = 0; i < tagovi.length; i++) {
            if (tagovi[i].equals(ja)) return true;
        }
        return false;
    }

    public String jacina() {
        if (slabo.isChecked()) return "slabo";
        if (srednje.isChecked()) return "srednje";
        if (jako.isChecked()) return "jako";
        return "sve";
    }

    public String[] uslovi() {
        String[] u = new String[4];
        u[0] = "";
        int k = 0;
        if (tekila.isChecked()) u[k++] = "tekila";
        if (vodka.isChecked()) u[k++] = "vodka";
        if (dzin.isChecked()) u[k++] = "dzin";
        if (rum.isChecked()) u[k++] = "rum";
        return u;
    }

    public void nazad(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(int pozicija) {
        if (!omiljeni) {
            dbHelper.dodajRed(kartice.get(pozicija).getId());
        } else {
            dbHelper.obrisiRed(kartice.get(pozicija).getId());
            kartice.remove(pozicija);
            Toast.makeText(this, "IZBRISANO", Toast.LENGTH_SHORT).show();
            filter(kartice);
        }
    }

    public ArrayList<Kartica> vratiSve() {
        ArrayList<Kartica> k = new ArrayList<>();
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "kokteli.json");
        Gson gson = new Gson();
        Type karticeTip = new TypeToken<List<Kartica>>() {
        }.getType();
        k = gson.fromJson(jsonFileString, karticeTip);
        for (int i = 0; i < k.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + k.get(i));
        }
        return k;
    }


    public ArrayList<Kartica> vratiOmiljene() {
        ArrayList<Kartica> sve = vratiSve();
        ArrayList<Kartica> k = new ArrayList<>();
        ArrayList<Integer> omilj = dbHelper.vratiOmiljene();

        for (int i = 0; i < sve.size(); i++) {
            if (omilj.contains(sve.get(i).getId())) k.add(sve.get(i));
        }

        return k;
    }
    public Drawable ucitajSliku(String s) throws IOException {
        InputStream ims = getAssets().open(s);
        return Drawable.createFromStream(ims, null);
    }


}
