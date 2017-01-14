package com.example.a20464654j.magiccards;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Fabian on 20/10/2016.
 */

class CridaApi {

    private static final String url_base = "https://api.magicthegathering.io/v1/cards";
    private static final int maxPag = 321;
    private static final int quantCartes = 100;


    static ArrayList<Carta> extrauCartes( ){


        ArrayList<Carta> cartes = new ArrayList<>();


        for (int i = 1; i <= 50; i++) {
        //per al maxim de pagines treure comentari del fori de baix i borrar/comentar el de dalt
        // en les 50 primeres cartes no hi ha cap carta Mythic Rare
        //for (int i = 1; i <= maxPag; i++) {

                try{
                    String url = extrauUrl( i );
                    String respostaJson = HttpUtils.get(url);

                    ArrayList<Carta> info = tractaJson( respostaJson );
                    cartes.addAll( info );

                } catch (IOException e) {

                    e.printStackTrace();
                }

        }


        return cartes;

    }

    private static String extrauUrl(int pagina){

        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .appendQueryParameter("page", String.valueOf( pagina ) )
                .appendQueryParameter("pageSize", String.valueOf( quantCartes ) )
                .build();

        return creaUri.toString();

    }


    private static ArrayList<Carta> tractaJson(String infoJSON){

        ArrayList<Carta> cartes = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(infoJSON);
            JSONArray cartesJSON = data.getJSONArray("cards");

            for (int i = 0; i < cartesJSON.length(); i++) {

                JSONObject cartaJSON = cartesJSON.getJSONObject(i);

                Carta carta = new Carta();
                carta.setNom( cartaJSON.getString("name"));
                carta.setTipo( cartaJSON.getString("type"));

                //Si la carta no te color li posa null. Si te colors els guarda tots
                if( cartaJSON.has("colors") ){
                    String colores = "";
                    for (int j = 0; j < cartaJSON.getJSONArray("colors").length(); j++) {
                        colores = colores + cartaJSON.getJSONArray("colors").getString( j ) + " ";
                    }
                    carta.setColor( colores );
                }else{
                    carta.setColor( null );
                }
                carta.setRaresa( cartaJSON.getString("rarity"));

                //Si la carta no te la URL de la imatge la posa a null
                if( cartaJSON.has( "imageUrl" ) ){
                    carta.setImatgeURL( cartaJSON.getString("imageUrl"));
                }else{
                    carta.setImatgeURL( null );
                }

                if( cartaJSON.has("text" ) ){
                    carta.setText( cartaJSON.getString( "text" ));
                }else{
                    carta.setText( null );
                }

                cartes.add(carta);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartes;

    }


}
