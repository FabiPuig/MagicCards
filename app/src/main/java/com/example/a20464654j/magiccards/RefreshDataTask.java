package com.example.a20464654j.magiccards;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.util.ArrayList;

/**
 * Created by 20464654j on 09/12/16.
 */
class RefreshDataTask extends AsyncTask<Void, Void, Void > {

    private Context context;

    RefreshDataTask( Context context ){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("inici-cargando");
    }

    @Override
    protected Void doInBackground(Void... params) {

        ArrayList<Carta> info = CridaApi.extrauCartes( );

        DataManager.borraCartes( context );
        DataManager.guardaCartes( info, context );

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Events.post("fin-cargando");
    }
}
