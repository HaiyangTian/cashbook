package com.example.sun.cashbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CashBean> cashBeanList = new ArrayList<>();
    private DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDataBaseHelper = new DataBaseHelper(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initCash();

        CashAdapter adapter = new CashAdapter(MainActivity.this,R.layout.cash_item,cashBeanList);

        ListView listView = (ListView)findViewById(R.id.cash_list);

        listView.setAdapter(adapter);


    }
    private void initCash(){
        mDataBaseHelper.deleteAllData();
          for (int i=0;i<6;i++){
              CashBean test = new CashBean();
              test.CashTitle = i + "mock";
              test.CashDate = "11-24";
              test.CashMoney = "21.3";
              mDataBaseHelper.insertDataBaseCost(test);
              //cashBeanList.add(test);
          }
        Cursor cursor = mDataBaseHelper.getDateBaseCost();
        if (cursor!=null){
            while(cursor.moveToNext()){
                CashBean cashBean = new CashBean();
                cashBean.CashTitle=cursor.getString(cursor.getColumnIndex("cost_title"));
                cashBean.CashDate=cursor.getString(cursor.getColumnIndex("cost_date"));
                cashBean.CashMoney=cursor.getString(cursor.getColumnIndex("cost_money"));
                cashBeanList.add(cashBean);
            }
            cursor.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
