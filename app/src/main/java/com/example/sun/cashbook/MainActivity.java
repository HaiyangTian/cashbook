package com.example.sun.cashbook;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View viewDialog =LayoutInflater.from(MainActivity.this).inflate(R.layout.cost_et,null);
                EditText title = viewDialog.findViewById(R.id.et_cost_title);
                EditText money = viewDialog.findViewById(R.id.et_cost_money);
                DatePicker date = viewDialog.findViewById(R.id.et_cost_date);
                builder.setView(viewDialog);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                builder.setPositiveButton("cancel",null);
                builder.create().show();
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
