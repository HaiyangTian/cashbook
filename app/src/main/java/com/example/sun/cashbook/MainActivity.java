package com.example.sun.cashbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.provider.ContactsContract;
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

import java.io.Serializable;
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
                initCash();

                final CashAdapter adapter = new CashAdapter(MainActivity.this,R.layout.cash_item,cashBeanList);

                ListView listView = (ListView)findViewById(R.id.cash_list);

                listView.setAdapter(adapter);
                final EditText title = viewDialog.findViewById(R.id.et_cost_title);
                final EditText money = viewDialog.findViewById(R.id.et_cost_money);
                final DatePicker date = viewDialog.findViewById(R.id.et_cost_date);
                builder.setView(viewDialog);
                builder.setTitle("new cost");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CashBean cashBean = new CashBean();
                        cashBean.CashTitle = title.getText().toString();
                        cashBean.CashMoney = money.getText().toString();
                        cashBean.CashDate = date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth();
                        mDataBaseHelper.insertDataBaseCost(cashBean);
                        cashBeanList.add(cashBean);
                        adapter.notifyDataSetChanged();
                    }

                });
                builder.setNegativeButton("cancel",null);
                builder.create().show();
            }
        });




    }
    private void initCash(){
        //mDataBaseHelper.deleteAllData();
          /*for (int i=0;i<6;i++){
              CashBean test = new CashBean();
              test.CashTitle = i + "mock";
              test.CashDate = "11-24";
              test.CashMoney = "21.3";
              mDataBaseHelper.insertDataBaseCost(test);
              //cashBeanList.add(test);
          }*/
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

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_char) {
             Intent intent = new Intent(MainActivity.this,ChartActivity.class);
            intent.putExtra("cost_list",(Serializable)cashBeanList);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
