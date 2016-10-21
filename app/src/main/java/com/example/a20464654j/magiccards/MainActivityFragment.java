package com.example.a20464654j.magiccards;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // ArrayList amb les cartes
    private ArrayList<String> alCartes;

    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Opcio per a mostrar el menu ( en aquest cas del MainActivityFragment)
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Creem un ListView que anira vinculat al list view del fragment_main
        ListView lvCartes = (ListView) view.findViewById(R.id.lvCards);

        // ArrayList temporal per a provar el ListView del fragment_main
        alCartes = new ArrayList<>();
        for( int i = 0; i < 1000; i++){
            alCartes.add("elemento #" + i);
        }

        // Adaptador per a incloure cada carta de l'ArrayList de les cartes al TextView del
        // lv_cartes_linea que anira dintre de cada posicio del ListView del fragment_main
        adapter = new ArrayAdapter<>(
                getContext(),               //en aquest fragment
                R.layout.lv_cartes_linea,   //layout on posara el TextView
                R.id.tvLinea,               //TextView text view en el que metera el contenido
                alCartes                    //contenido del ListView
        );

        // Enllaça el contingut de l'adaptador al ListView ( en aquest cas al ListView fragment_main)
        lvCartes.setAdapter( adapter );

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate( R.menu.menu_cartes_fragment, menu);
    }

    // quan seleccionem un objecte del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if( id == R.id.action_refresh){
            refresh();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh(){
        RefreshDataTask tasca = new RefreshDataTask();
        tasca.execute();
    }

    //Control de les AsyncTask
    private class RefreshDataTask extends AsyncTask<Void, Void, Void>{
        // AsyncTask de segon plano
        @Override
        protected Void doInBackground(Void... params) {

            CridaApi api = new CridaApi();
            ArrayList<Carta> info = api.extrauCartes();

            // Mostra el String info per el Logcat del Android Monitor
            Log.d("DEBUG", info.toString());

            return null;
        }
    }
}
