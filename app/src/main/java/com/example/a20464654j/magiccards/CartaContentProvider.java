package com.example.a20464654j.magiccards;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 20464654j on 18/11/16.
 */

public class CartaContentProvider extends CupboardContentProvider{

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static{
        cupboard().register( Carta.class );
    }


    public CartaContentProvider(){
        super(AUTHORITY, 1);
    }

}
