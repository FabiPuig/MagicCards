package com.example.a20464654j.magiccards;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalleActivityFragment extends Fragment {

    private View view;
    private ImageView ivCarta;
    private TextView tvNom;
    private TextView tvType;
    private TextView tvRarity;
    private TextView tvColor;
    private TextView tvText;

    public DetalleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate( R.layout.fragment_detalle, container, false);

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

        ivCarta = (ImageView) view.findViewById( R.id.ivCarta );
        tvNom = (TextView) view.findViewById( R.id.tvNom );
        tvType = (TextView) view.findViewById( R.id.tvType );
        tvRarity = (TextView) view.findViewById( R.id.tvRarity );
        tvColor = (TextView) view.findViewById( R.id.tvColor );

        tvNom.setText( carta.getNom() );
        tvType.setText( carta.getTipo() );
        tvRarity.setText( carta.getRaresa() );
        tvColor.setText( carta.getColor() );
        Glide.with( getContext() ).load( carta.getImatgeURL() ).into( ivCarta );
    }
}
