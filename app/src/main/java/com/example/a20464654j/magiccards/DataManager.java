package com.example.a20464654j.magiccards;

import android.content.Context;

import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
        String rarity = preferences.getString( "rarity", "All");
        String color = preferences.getString( "color", "All");

        String selection = "";
        String[] items;
        if( !color.equalsIgnoreCase( "All" ) && !rarity.equalsIgnoreCase( "All" ) ){
            selection = "( raresa =? ) AND ( color LIKE? )";
            items = new String[2];
            items[0] = rarity;
            items[1] = "%" + color + "%";
        }else if( color.equalsIgnoreCase( "All" ) && rarity.equalsIgnoreCase( "All" ) ){
            selection = null;
            items = null;
        }else{
            items = new String[1];
            if( !rarity.equalsIgnoreCase( "All" ) ){
                selection = " raresa =? ";
                items[0] =  rarity;
            }else{
                selection = " color LIKE? ";
                items[0] = "%" + color + "%";
            }
        }
        return  new CursorLoader( context, CARD_URI, null, selection, items, null);
    }
}
