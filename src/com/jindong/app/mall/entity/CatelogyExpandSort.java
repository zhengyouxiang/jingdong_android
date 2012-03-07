// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.json.JSONException;

public class CatelogyExpandSort
{

    public CatelogyExpandSort()
    {
    }

    CatelogyExpandSort(JSONObjectProxy jsonobjectproxy, int i)
    {
        selectedItem = 0;
        selectedSortOrder = 0;
        i;
        JVM INSTR tableswitch 1 1: default 32
    //                   1 33;
           goto _L1 _L2
_L1:
        return;
_L2:
        expandSortName = jsonobjectproxy.getStringOrNull("expandSortName");
        JSONArrayPoxy jsonarraypoxy = jsonobjectproxy.getJSONArrayOrNull("items");
        if(jsonarraypoxy != null && jsonarraypoxy.length() > 0)
        {
            keyList = new Integer[jsonarraypoxy.length()];
            valueList = new String[jsonarraypoxy.length()];
            LinkedHashMap linkedhashmap = new LinkedHashMap();
            int j = 0;
            while(j < jsonarraypoxy.length()) 
            {
                JSONObjectProxy jsonobjectproxy1 = jsonarraypoxy.getJSONObjectOrNull(j);
                Integer integer = jsonobjectproxy1.getIntOrNull("expandValueId");
                String s = jsonobjectproxy1.getStringOrNull("expandSortValueName");
                linkedhashmap.put(integer, s);
                keyList[j] = integer;
                valueList[j] = s;
                j++;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
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
        arraylist1.add(new CatelogyExpandSort(jsonarraypoxy.getJSONObject(j), i));
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

    public Integer[] getKeyList()
    {
        return keyList;
    }

    public int getSelectedItem()
    {
        return selectedItem;
    }

    public int getSelectedSortOrder()
    {
        return selectedSortOrder;
    }

    public String[] getValueList()
    {
        return valueList;
    }

    public void setKeyList(Integer ainteger[])
    {
        keyList = ainteger;
    }

    public void setSelectedItem(int i)
    {
        selectedItem = i;
    }

    public void setSelectedSortOrder(int i)
    {
        selectedSortOrder = i;
    }

    public void setValueList(String as[])
    {
        valueList = as;
    }

    public static final int CATELOGY_FILTER = 1;
    public String expandSortName;
    private Integer keyList[];
    private int selectedItem;
    private int selectedSortOrder;
    private String valueList[];
}
