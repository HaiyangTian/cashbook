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


    private static final String COST_TITLE = "cost_title";
    private static final String COST_DATE = "cost_date";
    private static final String COST_MONEY = "cost_money";
    private static final String SUN_COST = "sun_cost";

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
        cv.put(COST_TITLE,cb.CashTitle);
        cv.put(COST_DATE,cb.CashDate);
        cv.put(COST_MONEY,cb.CashMoney);
        database.insert(SUN_COST,null,cv);
        
    }

    public Cursor getDateBaseCost(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query(SUN_COST,null,null,null,null,null,COST_DATE +" ASC");
    }

    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(SUN_COST,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

