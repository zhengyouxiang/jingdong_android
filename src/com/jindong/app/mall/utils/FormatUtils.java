// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils
{

    public FormatUtils()
    {
    }

    public static Date parseDate(String s)
    {
        Date date1 = (new SimpleDateFormat("yyyy-MM-dd aahh:mm:ss")).parse(s);
        Date date = date1;
_L2:
        return date;
        ParseException parseexception;
        parseexception;
        parseexception.printStackTrace();
        date = null;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
