package com.example.a20464654j.magiccards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a20464654j.magiccards.databinding.LvCartesLineaBinding;

import java.util.List;

/**
 * Created by 20464654j on 11/11/16.
 */

public class CartaAdapter extends ArrayAdapter<Carta> {

    public CartaAdapter(Context context, int resource, List<Carta> objects){

        super(context,resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Carta carta = getItem( position);

        LvCartesLineaBinding binding = null;

        if( convertView == null){

            LayoutInflater inflater = LayoutInflater.from( getContext() );
            binding = DataBindingUtil.inflate( inflater,  R.layout.lv_cartes_linea, parent, false);
        }else{
            binding = DataBindingUtil.getBinding( convertView );
        }

        binding.tvNom.setText( carta.getNom() );
        binding.tvRarity.setText( carta.getRaresa() );
        binding.tvColor.setText( carta.getColor() );
        Glide.with( getContext() ).load( carta.getImatgeURL() ).into( binding.ivCarta );


        return binding.getRoot();
    }
}
