package com.example.a20464654j.magiccards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

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
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private CartaCursorAdapter adapter;

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

        adapter = new CartaCursorAdapter( getContext(), Carta.class);


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

        getLoaderManager().initLoader( 0, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader( getContext() );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor( data );
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor( null );
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

            DataManager.borraCartes( getContext() );
            DataManager.guardaCartes( info, getContext() );

            Log.d("DEBUG", info.toString());

            return null;
        }

    }
}
