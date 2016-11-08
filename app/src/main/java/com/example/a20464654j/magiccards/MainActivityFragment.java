package com.example.a20464654j.magiccards;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

    private int quantitat = 100;

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

        // ArrayList en el que estan les cartes que es mostraran al ListView del fragment_main
        alCartes = new ArrayList<>();


        // Adaptador per a incloure cada carta de l'ArrayList de les cartes al TextView del
        // lv_cartes_linea que anira dintre de cada posicio del ListView del fragment_main
        adapter = new ArrayAdapter<>(
                getContext(),               //en aquest fragment
                R.layout.lv_cartes_linea,   //layout on posara el TextView
                R.id.tvNom,               //TextView text view en el que metera el contenido
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
    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Carta>>{
        // AsyncTask de segon plano
        @Override
        protected ArrayList<Carta> doInBackground(Void... voids) {

            SharedPreferences preferencies = PreferenceManager.getDefaultSharedPreferences( getContext() );
            String rarity = preferencies.getString("rarity", "All");
            String color = preferencies.getString("color", "All");

            CridaApi api = new CridaApi();

            ArrayList<Carta> info = null;

            if( rarity.equals("All") && color.equals("All")){
                info = api.extrauCartes(quantitat);
            }else if( !rarity.equals("All") && color.equals("All")){
                info = api.cartesRarity(quantitat, rarity);
            }else if( rarity.equals("All") && !color.equals("All")){
                info = api.cartesColor(quantitat, color);
            }else {
                info = api.cartesRarityColor(quantitat, rarity, color );
            }

            Log.d("DEBUG", info.toString());

            return info;
        }

        @Override
        protected void onPostExecute(ArrayList<Carta> cartas) {
            adapter.clear();
            for (Carta c: cartas) {
                adapter.add( c.getNom() );
            }
        }
    }
}
