// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;

public class Comment
{

    public Comment()
    {
    }

    public Comment(JSONObjectProxy jsonobjectproxy, int i)
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        setTitle(jsonobjectproxy.getStringOrNull("title"));
        setScore(jsonobjectproxy.getIntOrNull("score"));
        setUserName(jsonobjectproxy.getStringOrNull("userId"));
        setInsertTime(jsonobjectproxy.getStringOrNull("creationTime"));
        setPros((new StringBuilder("\u4F18\u70B9\uFF1A ")).append(jsonobjectproxy.getStringOrNull("pros")).toString());
        setCons((new StringBuilder("\u4E0D\u8DB3:  ")).append(jsonobjectproxy.getStringOrNull("cons")).toString());
        setContent(jsonobjectproxy.getStringOrNull("content"));
        setReplyCount(jsonobjectproxy.getIntOrNull("replyCount"));
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
        arraylist1.add(new Comment(jsonarraypoxy.getJSONObject(j), i));
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

    public String getCons()
    {
        String s;
        if(cons != null)
            s = cons;
        else
            s = "\u6682\u65E0\u5185\u5BB9";
        return s;
    }

    public String getContent()
    {
        String s;
        if(content != null)
            s = content;
        else
            s = "\u6682\u65E0\u4F7F\u7528\u5FC3\u5F97";
        return s;
    }

    public String getInsertTime()
    {
        String s;
        if(insertTime != null)
            s = insertTime;
        else
            s = "\u6682\u65E0\u53D1\u8868\u65F6\u95F4";
        return s;
    }

    public String getPros()
    {
        String s;
        if(pros != null)
            s = pros;
        else
            s = "\u6682\u65E0\u5185\u5BB9";
        return s;
    }

    public Integer getReplyCount()
    {
        int i;
        if(replyCount != null)
            i = replyCount.intValue();
        else
            i = 0;
        return Integer.valueOf(i);
    }

    public Integer getScore()
    {
        int i;
        if(score != null)
            i = score.intValue();
        else
            i = 0;
        return Integer.valueOf(i);
    }

    public String getTitle()
    {
        String s;
        if(title != null)
            s = title;
        else
            s = "\u6682\u65E0\u6807\u9898";
        return s;
    }

    public String getUserName()
    {
        String s;
        if(userName != null)
            s = userName;
        else
            s = "\u6682\u65E0\u7528\u6237\u540D";
        return s;
    }

    public void setCons(String s)
    {
        cons = s;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setInsertTime(String s)
    {
        insertTime = s;
    }

    public void setPros(String s)
    {
        pros = s;
    }

    public void setReplyCount(Integer integer)
    {
        replyCount = integer;
    }

    public void setScore(Integer integer)
    {
        score = integer;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setUserName(String s)
    {
        userName = s;
    }

    public static final int COMMENT;
    private String cons;
    private String content;
    private String insertTime;
    private String pros;
    private Integer replyCount;
    private Integer score;
    private String title;
    private String userName;
}
