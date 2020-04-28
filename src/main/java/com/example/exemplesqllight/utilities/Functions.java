package com.example.exemplesqllight.utilities;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Functions {

    public static void getToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static String callWebService(HashMap<String,String> parameters, String methode)
    {
        //je construis l'url de mon appel service web à partir de la constant urlWS et de la méthode souhaitée dans mes parametres
        String url= ConstantBdd.urlWS + methode;


        //On instancie notre objet Okhttp
        OkHttpClient client = new OkHttpClient();

        //on créé une string pour contenir le résultat de notre requête service web
        String retourServiceWeb="";
        //Request : une classe propre à la library okhttp permettant de contenir notre requete WS
        //Ici on déclare notre objet request
        Request request;

        //on créée la demande à partir de l'url
        if(parameters.size() == 0)
        {
            //ici on instancie notre objet request
            request = new Request.Builder().url(url).build();
        }
        else
        {
            //on passe l'url dans notre objet httpBuilder
            HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();

            //on parcours tous nos parametres contenus dans notre HashMap et on les ajoutes à notre httpBuiler
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                httpBuider.addQueryParameter(key, val);
            }

            //on ajoute notre httpBuilder avec ses parametres dans notre objet request
            request = new Request.Builder().url(httpBuider.build()).build();
        }

        try {
            //on obtient la réponse
            Response response = client.newCall(request).execute();

            if (response.isSuccessful())
            {
                //on recupère au format Json
                retourServiceWeb = response.body().string();
            }
        }
        catch (Exception ex)
        {
            retourServiceWeb = ex.getMessage();
        }
        return retourServiceWeb;
    }

}
