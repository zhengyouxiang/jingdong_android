// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;

public class BuyAsk
{

    public BuyAsk(JSONObjectProxy jsonobjectproxy, int i)
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        setContent(jsonobjectproxy.getStringOrNull("content"));
        setCreationTime(jsonobjectproxy.getStringOrNull("creationTime"));
        setUserId(jsonobjectproxy.getStringOrNull("userId"));
        setReplyContent(jsonobjectproxy.getStringOrNull("replyContent"));
        setReplyTime(jsonobjectproxy.getStringOrNull("replyTime"));
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
        arraylist1.add(new BuyAsk(jsonarraypoxy.getJSONObject(j), i));
        j++;
        if(true) goto _L2; else goto _L1
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L3:
        if(Log.V)
            Log.v("Comment", "JSONException -->> ", jsonexception1);
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L3; else goto _L1
_L1:
        return arraylist;
    }

    public String getContent()
    {
        return (new StringBuilder(": ")).append(content).toString();
    }

    public String getCreationTime()
    {
        return creationTime;
    }

    public String getReplyContent()
    {
        return (new StringBuilder(": ")).append(replyContent).toString();
    }

    public String getReplyTime()
    {
        return replyTime;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setCreationTime(String s)
    {
        creationTime = s;
    }

    public void setReplyContent(String s)
    {
        replyContent = s;
    }

    public void setReplyTime(String s)
    {
        replyTime = s;
    }

    public void setUserId(String s)
    {
        userId = s;
    }

    public static final int CONSULTATION;
    private String content;
    private String creationTime;
    private String replyContent;
    private String replyTime;
    private String userId;
}
