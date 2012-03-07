// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

public class Coupon
    implements Serializable
{

    public Coupon(JSONObjectProxy jsonobjectproxy, int i)
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        setBalance(jsonobjectproxy.getIntOrNull("balance"));
        setType(jsonobjectproxy.getIntOrNull("bankType"));
        setMessage(jsonobjectproxy.getStringOrNull("message"));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        if(jsonarraypoxy != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist2 = null;
_L7:
        return arraylist2;
_L2:
        ArrayList arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        int j = 0;
_L4:
        if(j >= jsonarraypoxy.length())
        {
            arraylist = arraylist1;
            break; /* Loop/switch isn't completed */
        }
        arraylist1.add(new Coupon(jsonarraypoxy.getJSONObject(j), i));
        j++;
        if(true) goto _L4; else goto _L3
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L5:
        if(Log.V)
            Log.v("Ware", jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L5; else goto _L3
_L3:
        arraylist2 = arraylist;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public Integer getBalance()
    {
        return balance;
    }

    public String getMessage()
    {
        return message;
    }

    public Integer getType()
    {
        return type;
    }

    public void setBalance(Integer integer)
    {
        balance = integer;
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setType(Integer integer)
    {
        type = integer;
    }

    public static final int PROMOTION = 0;
    private static final long serialVersionUID = 0x104759b4b4446608L;
    private Integer balance;
    private String message;
    private Integer type;
}
