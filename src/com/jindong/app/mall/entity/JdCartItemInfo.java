// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;


public class JdCartItemInfo
{

    public JdCartItemInfo()
    {
    }

    public void addCount()
    {
        count = 1 + count;
    }

    public int getCount()
    {
        int i;
        if(count < 0)
            i = 0;
        else
            i = count;
        return i;
    }

    public String getProdID()
    {
        String s;
        if(sProdID == null)
            s = "";
        else
            s = sProdID;
        return s;
    }

    public String getProdName()
    {
        String s;
        if(sProdName == null)
            s = "";
        else
            s = sProdName;
        return s;
    }

    public String getProdPrice()
    {
        String s;
        if(sJdPrice == null)
            s = "";
        else
            s = sJdPrice;
        return s;
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setProdID(String s)
    {
        sProdID = s;
    }

    public void setProdName(String s)
    {
        sProdName = s;
    }

    public void setProdPrice(String s)
    {
        sJdPrice = s;
    }

    private int count;
    private String sJdPrice;
    private String sProdID;
    private String sProdName;
}
