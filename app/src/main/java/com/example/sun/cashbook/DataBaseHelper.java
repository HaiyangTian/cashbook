package com.example.sun.cashbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sun on 2017/9/12.
 */

public class DataBaseHelper extends SQLiteOpenHelper{


    public DataBaseHelper(Context context){
        super(context,"cashbook",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists sun_cost("+
                    "id integer primary key,"+
                    "cost_title varchar, "+
                    "cost_date varchar, "+
                    "cost_money varchar)");

    }
    public void insertDataBaseCost(CashBean cb){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cost_title",cb.CashTitle);
        cv.put("cost_date",cb.CashDate);
        cv.put("cost_money",cb.CashMoney);
        database.insert("sun_cost",null,cv);
        
    }

    public Cursor getDateBaseCost(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("sun_cost",null,null,null,null,null,"cost_date "+"ASC");
    }

    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("sun_cost",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

