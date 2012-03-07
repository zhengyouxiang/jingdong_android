// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;

public class Catelogy
{

    public Catelogy()
    {
    }

    Catelogy(JSONObjectProxy jsonobjectproxy, int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 78;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        cId = jsonobjectproxy.getStringOrNull("cid");
        level = jsonobjectproxy.getIntOrNull("level").intValue();
        name = jsonobjectproxy.getStringOrNull("name");
        orderSort = jsonobjectproxy.getIntOrNull("orderSort").intValue();
        continue; /* Loop/switch isn't completed */
_L3:
        setcId(jsonobjectproxy.getStringOrNull("cid"));
        setName(jsonobjectproxy.getStringOrNull("name"));
        setWareNumber(jsonobjectproxy.getIntOrNull("wareNumber"));
        if(true) goto _L1; else goto _L4
_L4:
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        ArrayList arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        int j = 0;
_L2:
        if(j >= jsonarraypoxy.length())
        {
            arraylist = arraylist1;
            break; /* Loop/switch isn't completed */
        }
        arraylist1.add(new Catelogy(jsonarraypoxy.getJSONObject(j), i));
        j++;
        if(true) goto _L2; else goto _L1
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L3:
        if(Log.V)
            Log.v("Ware", jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L3; else goto _L1
_L1:
        return arraylist;
    }

    public int getLevel()
    {
        return level;
    }

    public String getName()
    {
        return name;
    }

    public int getOrderSort()
    {
        return orderSort;
    }

    public Integer getWareNumber()
    {
        return wareNumber;
    }

    public String getcId()
    {
        return cId;
    }

    public void setLevel(int i)
    {
        level = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setOrderSort(int i)
    {
        orderSort = i;
    }

    public void setWareNumber(Integer integer)
    {
        wareNumber = integer;
    }

    public void setcId(String s)
    {
        cId = s;
    }

    public static final int CATELOGY = 0;
    public static final int CATELOGY_FILTER = 1;
    private String cId;
    private int level;
    private String name;
    private int orderSort;
    private Integer wareNumber;
}
