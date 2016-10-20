package com.example.a20464654j.magiccards;

import android.util.Log;
import java.io.IOException;

/**
 * Created by Fabian on 20/10/2016.
 */

public class CridaApi {

    private String url_base = "https://api.magicthegathering.io/v1/cards";


    String extrauCartes(){

        try{
            String respostaJson = HttpUtils.get(url_base);
            return respostaJson;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
