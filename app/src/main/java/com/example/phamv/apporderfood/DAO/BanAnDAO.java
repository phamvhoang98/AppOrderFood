package com.example.phamv.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.phamv.apporderfood.Database.CreateDatabase;

public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }
    public boolean addTables(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,name);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,false);

        long check = database.insert(CreateDatabase.TB_BANAN,null, contentValues);
        if(check != 0){
            return true;
        }
        else return false;
    }
}
