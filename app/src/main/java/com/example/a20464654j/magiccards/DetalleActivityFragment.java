package com.example.a20464654j.magiccards;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalleActivityFragment extends Fragment {

    public DetalleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_detalle, container, false);

        Intent i = getActivity().getIntent();

        if( i != null ){
            Carta carta = (Carta) i.getSerializableExtra( "carta" );

            if( carta != null ){
                updateUi(carta);
            }
        }
        return view;
    }

    private void updateUi(Carta carta){
        Log.d("CARTA", carta.toString() );
    }
}
