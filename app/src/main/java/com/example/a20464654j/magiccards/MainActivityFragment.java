package com.example.a20464654j.magiccards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
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
import android.widget.AdapterView;

import com.example.a20464654j.magiccards.databinding.FragmentMainBinding;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // ArrayList amb les cartes
    private ArrayList<Carta> alCartes;

    private CartaAdapter adapter;

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

        
        FragmentMainBinding binding = DataBindingUtil.inflate( inflater, R.layout.fragment_main,
                container, false);

        View view = binding.getRoot();

        // ArrayList en el que estan les cartes que es mostraran al ListView del fragment_main
        alCartes = new ArrayList<>();


        // Adaptador per a incloure cada carta de l'ArrayList de les cartes als TextView del
        // lv_cartes_linea que anira dintre de cada posicio del ListView del fragment_main
        adapter = new CartaAdapter(
                getContext(),               //en aquest fragment
                R.layout.lv_cartes_linea,   //layout on posara el TextView
                alCartes                    //contenido del ListView
        );


        binding.lvCards.setAdapter( adapter );

        // Definim el OnItemClickListener que ens permetra obrir la DetalleActivity de la carta
        // seleccionada del lv
        binding.lvCards.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Agafem la carta del ListView
                Carta carta = (Carta) adapterView.getItemAtPosition( position );

                // Creem el intent que llançara l'Activity dels detalls ( DetalleActivity )
                Intent intent = new Intent( getContext(), DetalleActivity.class);

                // Fiquem la carta dintre de l'intent
                intent.putExtra( "carta", carta);

                // Llancem l'intent
                startActivity( intent );
            }
        });

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

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    public void refresh(){
        RefreshDataTask tasca = new RefreshDataTask();
        tasca.execute();
    }

    //Control de les AsyncTask
    private class RefreshDataTask extends AsyncTask<Void, Void, Void>{
        // AsyncTask de segon plano
        @Override
        protected Void doInBackground(Void... voids) {

            //Carrega les preferencies
            SharedPreferences preferencies = PreferenceManager.getDefaultSharedPreferences( getContext() );
            //Guarda la rarity i el color de les preferencies
            String rarity = preferencies.getString("rarity", "All");
            String color = preferencies.getString("color", "All");

            ArrayList<Carta> info;

            if( rarity.equals("All") && color.equals("All")){
                info = CridaApi.extrauCartes(quantitat);
            }else if( !rarity.equals("All") && color.equals("All")){
                info = CridaApi.cartesRarity(quantitat, rarity);
            }else if( rarity.equals("All") && !color.equals("All")){
                info = CridaApi.cartesColor(quantitat, color);
            }else {
                info = CridaApi.cartesRarityColor(quantitat, rarity, color );
            }

            DataManager.guardaCartes( info, getContext() );

            Log.d("DEBUG", info.toString());

            return null;
        }

    }
}
