package com.example.koktelmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Recepti extends AppCompatActivity implements Adapter.MOnClickListener{

    ArrayList<Kartica> kartice;
    Adapter adapter;
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recepti_activity);


//        String jsongString = readFromFile();
//        try {
//            JSONArray jarray = new JSONArray(jsongString);
//            kartice = procitaj(jarray);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "kokteli.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type karticeTip = new TypeToken<List<Kartica>>() { }.getType();
        kartice = gson.fromJson(jsonFileString, karticeTip);
        for (int i = 0; i < kartice.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + kartice.get(i));
        }




//        kartice.add(new Kartica( "Lud", "sastojak1,sastojak2,sastojak3", "crno.png", "tekila,jak,votka", false));
//        kartice.add(new Kartica( "Drugi", "sastojak1,sastojak2,sastojak3", "crno.png", "tekila,jak,votka", false));
//        kartice.add(new Kartica( "Treci", "sastojak1,sastojak2,sastojak3", "crno.png","tekila,jak,votka", false));


        recyclerView = (RecyclerView) findViewById(R.id.listaRecepata);
        adapter = new Adapter(kartice, (Adapter.MOnClickListener) this);
        recyclerView.setAdapter(adapter);
    }


    public void sortirajPoVrsti(View view) {
    }


    public void sortirajPoJacini(View view) {
    }

    public void nazad(View view) {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(int pozicija) {

    }

    private String readFromFile() {

        String ret = "";
        InputStream inputStream = null;
        try {
            inputStream = openFileInput("src/main/assets/kokteli.json");
            Utils.getJsonFromAssets(getApplicationContext(), "bezkoder.json");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public ArrayList<Kartica> procitaj(JSONArray ja) throws JSONException {
        ArrayList<Kartica> k = new ArrayList<>();

        for (int i = 0; i < ja.length(); i++){
            JSONObject c = ja.getJSONObject(i);
            String id = c.getString("id");
            String naziv = c.getString("naziv");
            String recept = c.getString("recept");
            String slika = c.getString("slika");
            String tagovi = c.getString("tagovi");
            Boolean omiljen = c.getBoolean("omiljen");

            Kartica kar = new Kartica(naziv, recept, slika, tagovi, omiljen);
            k.add(kar);
        }
        return k;
    }
}
