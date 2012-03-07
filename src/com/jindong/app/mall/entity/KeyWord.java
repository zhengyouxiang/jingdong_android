// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.Log;
import java.util.LinkedList;
import org.json.*;

public class KeyWord
{

    public KeyWord()
    {
    }

    public KeyWord(JSONObject jsonobject, int i)
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        try
        {
            name = jsonobject.getString("wname");
            count = jsonobject.getInt("tipNumber");
        }
        catch(JSONException jsonexception)
        {
            if(Log.V)
                Log.v(com/jindong/app/mall/entity/KeyWord.getName(), jsonexception.getMessage());
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static LinkedList toList(JSONArray jsonarray, int i)
    {
        LinkedList linkedlist = null;
        LinkedList linkedlist1 = new LinkedList();
        int j = 0;
_L2:
        if(j >= jsonarray.length())
        {
            linkedlist = linkedlist1;
            break; /* Loop/switch isn't completed */
        }
        linkedlist1.add(new KeyWord(jsonarray.getJSONObject(j), i));
        j++;
        if(true) goto _L2; else goto _L1
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L3:
        if(Log.V)
            Log.v(com/jindong/app/mall/entity/KeyWord.getName(), jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        linkedlist = linkedlist1;
        if(true) goto _L3; else goto _L1
_L1:
        return linkedlist;
    }

    public int getCount()
    {
        return count;
    }

    public String getCountString()
    {
        return (new StringBuilder(String.valueOf(count))).append("\u6761\u7ED3\u679C").toString();
    }

    public String getName()
    {
        return name;
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String toString()
    {
        return name;
    }

    public static final int TIP;
    private int count;
    private String name;
}
