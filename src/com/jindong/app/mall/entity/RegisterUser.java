// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.util.LinkedList;
import org.json.JSONException;

public class RegisterUser
{

    public RegisterUser(JSONObjectProxy jsonobjectproxy, int i)
    {
        sRegUserName = jsonobjectproxy.getString("username");
        sRegFirstPwd = jsonobjectproxy.getString("pwd");
        sRegSecPwd = jsonobjectproxy.getString("pwd2");
        sRegMail = jsonobjectproxy.getString("mail");
        sUuid = jsonobjectproxy.getString("uuid");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
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
        linkedlist.add(new RegisterUser(jsonarraypoxy.getJSONObject(j), i));
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

    public String getRegMailAddr()
    {
        return sRegMail;
    }

    public String getRegUUID()
    {
        return sUuid;
    }

    public String getRegUserFisrtPwd()
    {
        return sRegSecPwd;
    }

    public String getRegUserName()
    {
        return sRegUserName;
    }

    public String getRegUserSecondPwd()
    {
        return sRegFirstPwd;
    }

    public void setRegMailAddr(String s)
    {
        sRegMail = s;
    }

    public void setRegUUID(String s)
    {
        sUuid = s;
    }

    public void setRegUserFisrtPwd(String s)
    {
        sRegFirstPwd = s;
    }

    public void setRegUserName(String s)
    {
        sRegUserName = s;
    }

    public void setRegUserSecondPwd(String s)
    {
        sRegSecPwd = s;
    }

    private String sRegFirstPwd;
    private String sRegMail;
    private String sRegSecPwd;
    private String sRegUserName;
    private String sUuid;
}
