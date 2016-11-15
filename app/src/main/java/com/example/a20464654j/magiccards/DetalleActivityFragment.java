package com.example.a20464654j.magiccards;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a20464654j.magiccards.databinding.FragmentDetalleBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalleActivityFragment extends Fragment {

    private FragmentDetalleBinding binding;

    public DetalleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detalle, container, false);
        View view = binding.getRoot();

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

        binding.tvNom.setText( carta.getNom() );
        binding.tvType.setText( carta.getTipo() );
        binding.tvColor.setText( carta.getColor() );
        binding.tvRarity.setText( carta.getRaresa() );
        binding.tvText.setText( carta.getText() );

        Glide.with( getContext() ).load( carta.getImatgeURL() ).into( binding.ivCarta );
    }
}
