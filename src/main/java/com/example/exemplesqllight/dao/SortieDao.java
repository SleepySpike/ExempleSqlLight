package com.example.exemplesqllight.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.exemplesqllight.entities.Sortie;
import com.example.exemplesqllight.entities.Sorties;
import com.example.exemplesqllight.utilities.ConstantBdd;
import com.example.exemplesqllight.dao.BaseDao;
import com.example.exemplesqllight.dao.InterfaceDao;

import java.util.ArrayList;


public class SortieDao extends BaseDao implements InterfaceDao<Sortie> {

    public SortieDao(Context context) {
        super(context);
    }

    @Override
    public void openForWrite() {

    }

    @Override
    public void close() {

    }

    @Override
    public void insert(Sortie entity) {
        try {

            ContentValues content = new ContentValues();
            content.put(ConstantBdd.COL_ID_SORTIE, entity.getIdSortie());
            content.put(ConstantBdd.COL_NOM_SORTIE, entity.getNom());
            content.put(ConstantBdd.COL_DESCRIPTION_SORTIE, entity.getDescription());

            mSQLiteDatabase.insert(ConstantBdd.TABLE_SORTIES, null, content);
        } catch (Exception ex) {
            Log.e("InsertSortie", ex.getMessage());
        }
    }

    @Override
    public int update(Sortie entity) {
        ContentValues content = new ContentValues();
        content.put(ConstantBdd.COL_NOM_SORTIE, entity.getNom());
        content.put(ConstantBdd.COL_DESCRIPTION_SORTIE, entity.getDescription());

        return mSQLiteDatabase.update(ConstantBdd.TABLE_SORTIES,
                content, ConstantBdd.COL_ID_SORTIE + " = " + entity.getIdSortie(),
                null);
    }

    @Override
    public int delete(int id) {
        return mSQLiteDatabase.delete(ConstantBdd.TABLE_SORTIES,
                ConstantBdd.COL_ID_SORTIE + " = " + id,null);
    }

    @Override
    public Sortie get(int id) {
        try{}catch (Exception ex){}

        Cursor c = mSQLiteDatabase.query(ConstantBdd.TABLE_SORTIES,
                new String[]{ConstantBdd.COL_ID_SORTIE,
                        ConstantBdd.COL_NOM_SORTIE,
                        ConstantBdd.COL_DESCRIPTION_SORTIE},
                ConstantBdd.COL_ID_SORTIE + " = " + id,
                null, null,
                null, ConstantBdd.COL_ID_SORTIE);

        Sorties sorties = cursorToSortie(c);

        return sorties.size() == 1? sorties.get(0): null;
    }

    @Override
    public Sorties getAll() {
        try{
            Cursor c = mSQLiteDatabase.query(ConstantBdd.TABLE_SORTIES,
                    new String[] { ConstantBdd.COL_ID_SORTIE,
                            ConstantBdd.COL_NOM_SORTIE,
                            ConstantBdd.COL_DESCRIPTION_SORTIE},
                    null,
                    null,
                    null,
                    null,ConstantBdd.COL_NOM_SORTIE);

            return  cursorToSortie(c);

        }
        catch (Exception ex){

        }

        return null;
    }

    public Sorties cursorToSortie(Cursor c) {

        Sorties sorties = new Sorties();
        Sortie sortie = null;

        if(c.getCount() > 0)
        {
            for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
                sortie = new Sortie();
                sortie.setIdSortie(c.getInt(ConstantBdd.NUM_COL_ID_SORTIE));
                sortie.setNom(c.getString(ConstantBdd.NUM_COL_NOM_SORTIE));
                sortie.setDescription(c.getString(ConstantBdd.NUM_COL_DESCRIPTION_SORTIE));

                sorties.add(sortie);
            }
        }
        c.close();

        return sorties;
    }
}