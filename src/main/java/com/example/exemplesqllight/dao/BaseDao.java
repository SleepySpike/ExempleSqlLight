package com.example.exemplesqllight.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.exemplesqllight.utilities.ConstantBdd;

public class BaseDao {

    SqliteDao mSqliteDao;
    public SQLiteDatabase mSQLiteDatabase;

    public SqliteDao getSqliteDao() {
        return mSqliteDao;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    public BaseDao(Context context)
    {
        //instancie notre bdd
        mSqliteDao = new SqliteDao(context, ConstantBdd.NOM_BDD,null,ConstantBdd.VERSION);
    }

    public void openForWrite()
    {
        mSQLiteDatabase = mSqliteDao.getWritableDatabase();
    }

    public void close()
    {
        mSQLiteDatabase.close();
    }
}
