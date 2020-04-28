package com.example.exemplesqllight.utilities;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import java.util.HashMap;

import kotlin.Function;
import kotlin.reflect.KParameter;

public class AsyncServiceWeb extends AsyncTask<String,String,String> {

    String _methodSW;
    HashMap<String,String> _parameters;
    String _retourWS;

    public AsyncServiceWeb(String methodeSW, @Nullable HashMap<String,String> parameters)
    {
        _methodSW = methodeSW;
        _parameters = parameters;
    }

    @Override
    protected String doInBackground(String... strings) {
        return Functions.callWebService(_parameters,_methodSW);
    }

    @Override
    protected void onPostExecute(String json)
    {
        super.onPostExecute(json);
        this._retourWS = json;
    }
}
