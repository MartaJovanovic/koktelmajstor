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


    // u ovoj metodi postavljamo podatke, konkretne vrednosti, stavke nase listu na UI
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // U KOLIKO NEKOME ISKOCI GRESKA DA NE MOZE DA SE PROSLEDI POZICIJA JER JE DEPRICATED
        // SVUDA UMESTO NJE PROSLEDITE holder.getAdapterPosition();



        Kartica kartica = lista.get(position);
        holder.naslov.setText(kartica.getNaziv());
        holder.recept.setText(kartica.getRecept());

        if (mContext instanceof Recepti) {
            try {
                holder.imageView.setImageDrawable(((Recepti)mContext).ucitajSliku("mohitoN.jpg"));
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



//    public void postaviSlike() {
//        File directory = new File(path); //path is the string specifying your directory path.
//        File[] files = directory.listFiles();
//        for (int i = 0; i < files.length; i++) {
//            Log.d("Files", "FileName:" + files[i].getName()); //these are the different filenames in the directory
//
////You can now use these file names along with the directory path and convert the image there to a bitmap and set it to recycler view's image view
//
//            File imgFile = new File(path + files[i].getName());
//            if (imgFile.exists()) {
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()); //this is the bitmap for the image
//
//                ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);//your image view in the recycler view
//
//                myImage.setImageBitmap(myBitmap);//image set to the image view
//
//            }
//            ;
//        }
////And include this permission in the manifest file:
//
////<manifest >
////<uses - permission android:
////        name = "android.permission.READ_EXTERNAL_STORAGE" / >
////<uses - permission android:
////        name = "android.permission.WRITE_EXTERNAL_STORAGE" / >
////            ...
////    <application >
////        ...
////        <activity >
////            ...
////        </activity >
////    </application >
////</manifest >
//
////For API 23+ you need to request the read/write permissions even if they are already in your manifest.
//
//        // Storage Permissions
//        private static final int REQUEST_EXTERNAL_STORAGE = 1;
//        private static String[] PERMISSIONS_STORAGE = {
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//        };
//
//        /**
//         * Checks if the app has permission to write to device storage
//         *
//         * If the app does not has permission then the user will be prompted to grant permissions
//         *
//         * @param activity
//         */
//        public static void verifyStoragePermissions (Activity activity){
//            // Check if we have write permission
//            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//            if (permission != PackageManager.PERMISSION_GRANTED) {
//                // We don't have permission so prompt the user
//                ActivityCompat.requestPermissions(
//                        activity,
//                        PERMISSIONS_STORAGE,
//                        REQUEST_EXTERNAL_STORAGE
//                );
//            }
//        }
//
//        //And handle the responce , some example:
//        @Override
//        public void onRequestPermissionsResult ( int requestCode,
//        String permissions[], int[] grantResults){
//            switch (requestCode) {
//                case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//                    // If request is cancelled, the result arrays are empty.
//                    if (grantResults.length > 0
//                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                        // permission was granted, yay! Do the
//                        // contacts-related task you need to do.
//
//                    } else {
//
//                        // permission denied, boo! Disable the
//                        // functionality that depends on this permission.
//                    }
//                    return;
//                }
//
//                // other 'case' lines to check for other
//                // permissions this app might request
//            }
//        }
//
////For official documentation about requesting permissions for API 23+, check https://developer.android.com/training/permissions/requesting.html
//    }
}
