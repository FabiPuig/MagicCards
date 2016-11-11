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

public class CridaApi {

    private final String url_base = "https://api.magicthegathering.io/v1/cards";


    ArrayList<Carta> extrauCartes(int quantitat){

        //Uri utilitzada per a filtrar les cridades a la api
        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .appendQueryParameter("pageSize", String.valueOf(quantitat))
                .build();
        String urlFinal = creaUri.toString();

        return crida(urlFinal);

    }

    ArrayList<Carta> cartesRarity(int quantitat, String rarity){

        //Uri utilitzada per a filtrar les cridades a la api
        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .appendQueryParameter("pageSize", String.valueOf(quantitat))
                .appendQueryParameter("rarity", rarity)
                .build();
        String urlFinal = creaUri.toString();

        return crida(urlFinal);


    }

    ArrayList<Carta> cartesColor(int quantitat, String color){

        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .appendQueryParameter("pageSize", String.valueOf(quantitat) )
                .appendQueryParameter("colors", color)
                .build();
        String urlFinal = creaUri.toString();

        return crida(urlFinal);
    }

    ArrayList<Carta> cartesRarityColor(int quantitat, String rarity, String color){

        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .appendQueryParameter("pageSize", String.valueOf(quantitat) )
                .appendQueryParameter("rarity", rarity)
                .appendQueryParameter("colors", color)
                .build();
        String urlFinal = creaUri.toString();

        Log.d("DEBUG", urlFinal);

        return crida(urlFinal);

    }


    private ArrayList<Carta> crida(String url){

        try{

            String respostaJson = HttpUtils.get(url);
            return tractaJson(respostaJson);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;

    }

    private ArrayList<Carta> tractaJson(String infoJSON){

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

                cartes.add(carta);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartes;

    }


}
