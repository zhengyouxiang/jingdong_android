// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.Log;
import java.io.Serializable;
import java.util.LinkedList;
import org.json.*;

public class Image
    implements Serializable
{

    public Image(String s, String s1)
    {
        small = s;
        big = s1;
    }

    public Image(JSONObject jsonobject, int i)
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
            setSmall(jsonobject.getString("newpath"));
            setBig(jsonobject.getString("bigpath"));
        }
        catch(JSONException jsonexception)
        {
            if(Log.V)
                Log.v(com/jindong/app/mall/entity/Image.getName(), jsonexception.getMessage());
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static LinkedList toList(JSONArray jsonarray, int i)
    {
        if(jsonarray != null) goto _L2; else goto _L1
_L1:
        LinkedList linkedlist2 = null;
_L7:
        return linkedlist2;
_L2:
        LinkedList linkedlist = null;
        LinkedList linkedlist1 = new LinkedList();
        int j = 0;
_L4:
        if(j >= jsonarray.length())
        {
            linkedlist = linkedlist1;
            break; /* Loop/switch isn't completed */
        }
        linkedlist1.add(new Image(jsonarray.getJSONObject(j), i));
        j++;
        if(true) goto _L4; else goto _L3
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L5:
        if(Log.V)
            Log.v(com/jindong/app/mall/entity/Image.getName(), jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        linkedlist = linkedlist1;
        if(true) goto _L5; else goto _L3
_L3:
        linkedlist2 = linkedlist;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public String getBig()
    {
        return big;
    }

    public String getSmall()
    {
        return small;
    }

    public void setBig(String s)
    {
        big = s;
    }

    public void setSmall(String s)
    {
        small = s;
    }

    public static final int PRODUCTDETAIL = 0;
    private static final long serialVersionUID = 0x6fa3f243eaca8921L;
    private String big;
    private String small;
}
