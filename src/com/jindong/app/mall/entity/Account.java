// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.Date;
import java.util.LinkedList;
import org.json.JSONException;

public class Account
{

    public Account(JSONObjectProxy jsonobjectproxy, int i)
    {
        mUserName = jsonobjectproxy.getString("loginname");
        mPassword = jsonobjectproxy.getString("loginpwd");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public Account(String s, String s1)
    {
        mUserName = s;
        String _tmp = mPassword;
    }

    public static LinkedList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        LinkedList linkedlist = new LinkedList();
        int j = 0;
_L2:
        LinkedList linkedlist1;
        if(j >= jsonarraypoxy.length())
        {
            linkedlist1 = linkedlist;
            break; /* Loop/switch isn't completed */
        }
        linkedlist.add(new Account(jsonarraypoxy.getJSONObject(j), i));
        j++;
        if(true) goto _L2; else goto _L1
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L3:
        if(Log.V)
            Log.v("Ware", jsonexception1.getMessage());
        linkedlist1 = null;
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        if(true) goto _L3; else goto _L1
_L1:
        return linkedlist1;
    }

    public String getUserName()
    {
        return mUserName;
    }

    public String getUserPassword()
    {
        return mPassword;
    }

    public void setUserName(String s)
    {
        mUserName = s;
    }

    public void setUserPassword(String s)
    {
        mPassword = s;
    }

    public static final int LOGIN = 1;
    private Date mCurrentLoginDate;
    private Date mLastLoginDate;
    private String mLatestVisitedProducts[];
    private String mPassword;
    private String mUserName;
}
