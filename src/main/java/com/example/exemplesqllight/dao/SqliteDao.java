package com.example.exemplesqllight.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.exemplesqllight.utilities.ConstantBdd;

public class SqliteDao extends SQLiteOpenHelper {

    public SqliteDao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            Log.e("SQLITE", "Création bdd & table");
            db.execSQL(ConstantBdd.CREATE_TABLE_SORTIE);
        }
        catch (Exception e)
        {
            Log.e("TAG",e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
