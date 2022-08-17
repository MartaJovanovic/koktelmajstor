package com.example.koktelmajstor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDBHelperOmiljeni extends SQLiteOpenHelper {
    public static final String NAZIV_TABELE = "omiljeni";
    Context context;


    public MyDBHelperOmiljeni(@Nullable Context context) {
        super(context,  "dbOmiljeni.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + NAZIV_TABELE + " ( id integer primary key)");
        } catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAZIV_TABELE);
        onCreate(db);
    }

    public void dodajRed(int id){
        ArrayList<Integer> lista = vratiOmiljene();
        if(lista.contains(id)) {
            Toast.makeText(context,"Vec je u omiljenim",Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vrednost = new ContentValues();
        vrednost.put("id",id);

        long r = db.insert(NAZIV_TABELE, null, vrednost);
        db.close();
        if (r==-1){
            Toast.makeText(context,"NIJE DODATO",Toast.LENGTH_SHORT).show();
        }
        else {Toast.makeText(context,"USPESNO DODATO U OMILJENE",Toast.LENGTH_SHORT).show();}
    }

    public void obrisiRed(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(NAZIV_TABELE,"id = ?",new String[]{String.valueOf(id)});
        db.close();

    }

    public void obrisiSve(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + NAZIV_TABELE);
        db.close();
    }

    public ArrayList<Integer> vratiOmiljene(){
        Cursor cursor;
        ArrayList<Integer> omiljeni = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + NAZIV_TABELE, null);

        while(cursor.moveToNext()){
            omiljeni.add(cursor.getInt(0));
        }
        return omiljeni;
    }

}