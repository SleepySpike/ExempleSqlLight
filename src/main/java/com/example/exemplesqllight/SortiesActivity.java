package com.example.exemplesqllight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.exemplesqllight.entities.Sortie;
import com.example.exemplesqllight.entities.SortieDao;
import com.example.exemplesqllight.entities.Sorties;
import com.example.exemplesqllight.utilities.Functions;
import com.google.gson.Gson;

import java.util.HashMap;

public class SortiesActivity extends AppCompatActivity {

    Sorties sorties;
    RecyclerView mRecyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorties);

        context = this;

        mRecyclerView = findViewById(R.id.rcwSorties);

        SortiesTask sortiesTask = new SortiesTask();
        sortiesTask.execute();
    }

    private class SortiesTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... voids) {

            HashMap<String,String> parameters = new HashMap<>();
            parameters.put("IdAssociation","8");

            //appel service web
            return Functions.callWebService(parameters,"getSorties");
        }

        @Override
        protected void onPostExecute(String sortiesJson) {
            super.onPostExecute(sortiesJson);

            Gson gson = new Gson();

            try {
                sorties = gson.fromJson(sortiesJson, Sorties.class);
                SortieDao sortieDao = new SortieDao(context);
                sortieDao.openForWrite();

                for(Sortie sortie:sorties)
                {
                    sortieDao.insert(sortie);
                }

                sortieDao.close();
            }
            catch (Exception ex)
            {
                String erreur = ex.getMessage();
            }
        }
    }

}
