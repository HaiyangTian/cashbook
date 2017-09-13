package com.example.sun.cashbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;



/**
 * Created by Sun on 2017/9/11.
 */

public class CashAdapter extends ArrayAdapter<CashBean>{

    private int resourced;
    public CashAdapter(Context context, int textViewId, List<CashBean> objects){
        super(context,textViewId,objects);
        resourced = textViewId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        CashBean cashBean  = getItem(position);
        ViewHolder viewHolder;
        View view;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourced,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.costTitle = (TextView)view.findViewById(R.id.cash_title);
            viewHolder.costDate = (TextView)view.findViewById(R.id.cash_date);
            viewHolder.costMoney = (TextView)view.findViewById(R.id.cash_money);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.costTitle.setText(cashBean.getCashTitle());
        viewHolder.costDate.setText(cashBean.getCashDate());
        viewHolder.costMoney.setText(cashBean.getCashMoney());
        return view;
    }
    public  class ViewHolder{
        TextView costTitle;
        TextView costDate;
        TextView costMoney;
    }

}
