package com.example.exemplesqllight;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplesqllight.entities.Sortie;
import com.example.exemplesqllight.entities.SortieDao;
import com.example.exemplesqllight.entities.Sorties;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        ListView lswSorties = findViewById(R.id.lsvSorties);

        Sortie sortie = new Sortie();
        sortie.setIdSortie(3);
        sortie.setNom("Sortie cinema");
        sortie.setDescription("Sortie apprentissage");

        SortieDao sortieDao = new SortieDao(context);

        sortieDao.openForWrite();
        sortieDao.insert(sortie);

        //Je reécupère la liste des sorties
        Sorties sorties = sortieDao.getAll();
        sortieDao.close();

        ArrayList<String> nomSorties = new ArrayList<>();

        for (Sortie item:sorties)
        {
            nomSorties.add(item.getNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, nomSorties);

        lswSorties.setAdapter(adapter);

    }
}
