package com.example.a20464654j.magiccards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a20464654j.magiccards.databinding.LvCartesLineaBinding;

/**
 * Created by 20464654j on 22/11/16.
 */

public class CartaCursorAdapter extends CupboardCursorAdapter<Carta>{

    public CartaCursorAdapter(Context context, Class<Carta> entityClass){

        super(context, entityClass);

    }

    @Override
    public View newView(Context context, Carta carta, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from( context );
        LvCartesLineaBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.lv_cartes_linea, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Carta model) {
        LvCartesLineaBinding binding = DataBindingUtil.getBinding( view );
        binding.tvNom.setText( model.getNom() );
        binding.tvRarity.setText( model.getRaresa() );
        binding.tvColor.setText( model.getColor() );
        Glide.with( context ).load( model.getImatgeURL() ).into( binding.ivCarta );
    }
}
