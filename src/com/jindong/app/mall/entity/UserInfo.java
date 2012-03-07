// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import org.json.JSONObject;

public class UserInfo
{

    public UserInfo()
    {
        jbAddr = new JSONObject();
    }

    public UserInfo(String s, String s1, String s2, String s3, JSONObject jsonobject)
    {
        sUserName = s;
        sMoble = s1;
        sZip = s2;
        where = s3;
        jbAddr = new JSONObject();
        jbAddr = jsonobject;
    }

    public JSONObject getUserAddr()
    {
        return jbAddr;
    }

    public String getUserMobile()
    {
        return sMoble;
    }

    public String getUserName()
    {
        return sUserName;
    }

    public String getUserPhone()
    {
        return sPhone;
    }

    public String getUserWhere()
    {
        return where;
    }

    public String getUserZip()
    {
        return sZip;
    }

    public void setUserAddr(JSONObject jsonobject)
    {
        jbAddr = new JSONObject();
        jbAddr = jsonobject;
    }

    public void setUserMobile(String s)
    {
        sMoble = s;
    }

    public void setUserName(String s)
    {
        sUserName = s;
    }

    public void setUserPhone(String s)
    {
        sPhone = s;
    }

    public void setUserWhere(String s)
    {
        where = s;
    }

    public void setUserZip(String s)
    {
        sZip = s;
    }

    private JSONObject jbAddr;
    private String sMoble;
    private String sPhone;
    private String sUserName;
    private String sZip;
    private String where;
}
