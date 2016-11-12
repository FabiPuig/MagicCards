package com.example.a20464654j.magiccards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        if( convertView == null){

            LayoutInflater inflater = LayoutInflater.from( getContext() );
            convertView = inflater.inflate( R.layout.lv_cartes_linea, parent, false);

        }

        TextView tvNom = (TextView) convertView.findViewById( R.id.tvNom );
        TextView tvRarity = (TextView) convertView.findViewById( R.id.tvRarity );
        TextView tvColor = (TextView) convertView.findViewById( R.id.tvColor );
        ImageView ivCarta = (ImageView) convertView.findViewById( R.id.ivCarta );

        tvNom.setText( carta.getNom() );
        tvRarity.setText( carta.getRaresa() );
        tvColor.setText( carta.getColor() );
        Glide.with( getContext() ).load( carta.getImatgeURL() ).into( ivCarta );


        return convertView;
    }
}
