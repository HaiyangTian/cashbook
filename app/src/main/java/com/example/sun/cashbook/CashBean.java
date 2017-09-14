package com.example.sun.cashbook;

import java.io.Serializable;

/**
 * Created by Sun on 2017/9/11.
 */

public class CashBean implements Serializable {
    public String CashTitle;
    public String CashDate;
    public String CashMoney;


    public String getCashTitle(){
        return CashTitle;

    }
    public  String getCashDate(){
        return  CashDate;
    }
    public String getCashMoney(){
        return  CashMoney;
    }

}
