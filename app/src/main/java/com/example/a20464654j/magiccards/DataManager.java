package com.example.a20464654j.magiccards;

import android.content.Context;

import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 20464654j on 18/11/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with( CartaContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri( Carta.class );

    static void guardaCartes(ArrayList<Carta> cartes, Context context){

        cupboard().withContext( context ).put( CARD_URI, Carta.class, cartes);

    }

    static void borraCartes( Context context){
        cupboard().withContext( context ).delete( CARD_URI, "_id > ?",  "0");
    }

    static CursorLoader getCursorLoader(Context context){
        return  new CursorLoader( context, CARD_URI, null, null, null, null);
    }
}
