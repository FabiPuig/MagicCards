package com.example.a20464654j.magiccards;

import android.net.Uri;
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


    ArrayList<Carta> extrauCartes(){

        //Uri utilitzada per a filtrar les cridades a la api
        Uri creaUri = Uri.parse(url_base)
                .buildUpon()
                .build();
        String urlFinal = creaUri.toString();

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
                carta.setRaresa( cartaJSON.getString("rarity"));
                carta.setImatgeURL( cartaJSON.getString("imageUrl"));

                cartes.add(carta);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartes;

    }


}
