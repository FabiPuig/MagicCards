package com.example.a20464654j.magiccards;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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

        ListView lvCartes = (ListView) view.findViewById(R.id.lvCards);

        // ArrayList temporal per a provar el ListView del fragment_main
        alCartes = new ArrayList<>();
        for( int i = 0; i < 1000; i++){
            alCartes.add("elemento #" + i);
        }

        // Adaptador per a incloure cada carta de l'ArrayList de les cartes al TextView del lv_cartes_linea
        // que anira dintre de cada posicio del ListView del fragment_main
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_cartes_linea,
                R.id.tvLinea,
                alCartes
        );

        // Per a cada carta del ListView inclou un adaptador
        lvCartes.setAdapter( adapter );

        return view;
    }
}
