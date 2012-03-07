// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;

public class JdNews
{

    JdNews()
    {
    }

    public JdNews(JSONObjectProxy jsonobjectproxy, int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 29
    //                   1 90;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_90;
_L4:
        return;
_L2:
        try
        {
            setJdNewsId(jsonobjectproxy.getInt("jdNewsId"));
            setTitle(jsonobjectproxy.getString("title"));
            setAddTime(jsonobjectproxy.getString("addTime"));
            setStartTime(jsonobjectproxy.getString("startTime"));
            setEndTimel(jsonobjectproxy.getString("endTimel"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
          goto _L4
        setAddTime(jsonobjectproxy.getString("addTime"));
        setContent(jsonobjectproxy.getString("content"));
        setTitle(jsonobjectproxy.getString("title"));
          goto _L4
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
        arraylist1.add(new JdNews(jsonarraypoxy.getJSONObject(j), i));
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

    public String getAddTime()
    {
        return addTime;
    }

    public String getContent()
    {
        return content;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public int getJdNewsId()
    {
        return jdNewsId;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAddTime(String s)
    {
        addTime = s;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setEndTimel(String s)
    {
        endTime = s;
    }

    public void setJdNewsId(int i)
    {
        jdNewsId = i;
    }

    public void setStartTime(String s)
    {
        startTime = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public static final int JDNEWS = 0;
    public static final int JDNEWS_DETAIL = 1;
    private String addTime;
    private String content;
    private String endTime;
    private int jdNewsId;
    private String startTime;
    private String title;
}
