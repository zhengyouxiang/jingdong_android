// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import org.json.JSONObject;

public class InvoiceInfo
{

    public InvoiceInfo()
    {
        COMMON_INCOICE = 1;
        hasBook = false;
    }

    public JSONObject getInvoiceInfo()
    {
        return jbInvoiceInfo;
    }

    public int getInvoiceTitleType()
    {
        return InvoiceTitleType;
    }

    public int getInvoiceType()
    {
        return COMMON_INCOICE;
    }

    public JSONObject getInvoinceBooks()
    {
        return jbBooks;
    }

    public JSONObject getInvoinceGenerals()
    {
        return jbGenerals;
    }

    public JSONObject getInvoinceHeaders()
    {
        return jbHeaders;
    }

    public JSONObject getInvoinceTypes()
    {
        return jbTypes;
    }

    public boolean hasBook()
    {
        return hasBook;
    }

    public void setInvoiceInfo(JSONObject jsonobject)
    {
        jbInvoiceInfo = jsonobject;
    }

    public void setInvoiceInfo(boolean flag, JSONObject jsonobject)
    {
        hasBook = flag;
        jbInvoiceInfo = jsonobject;
    }

    public void setInvoiceTitleType(int i)
    {
        InvoiceTitleType = i;
    }

    public void setInvoiceType(int i)
    {
        COMMON_INCOICE = i;
    }

    public void setInvoinceBooks(JSONObject jsonobject)
    {
        jbBooks = jsonobject;
    }

    public void setInvoinceGenerals(JSONObject jsonobject)
    {
        jbGenerals = jsonobject;
    }

    public void setInvoinceHeaders(JSONObject jsonobject)
    {
        jbHeaders = jsonobject;
    }

    public void setInvoinceTypes(JSONObject jsonobject)
    {
        jbTypes = jsonobject;
    }

    public int COMMON_INCOICE;
    public int InvoiceTitleType;
    public boolean hasBook;
    private JSONObject jbBooks;
    private JSONObject jbGenerals;
    private JSONObject jbHeaders;
    public JSONObject jbInvoiceInfo;
    private JSONObject jbTypes;
}
