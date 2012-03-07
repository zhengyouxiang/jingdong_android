// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import com.jindong.app.mall.config.Configuration;

public class Log
{

    public Log()
    {
    }

    public static void d(String s, String s1)
    {
        if(printLog)
            android.util.Log.d(s, s1);
    }

    public static void d(String s, String s1, Throwable throwable)
    {
        if(printLog)
            android.util.Log.d(s, s1, throwable);
    }

    public static void e(String s, String s1)
    {
        if(printLog)
            android.util.Log.e(s, s1);
    }

    public static void e(String s, String s1, Throwable throwable)
    {
        if(printLog)
            android.util.Log.e(s, s1, throwable);
    }

    public static void i(String s, String s1)
    {
        if(printLog)
            android.util.Log.i(s, s1);
    }

    public static void i(String s, String s1, Throwable throwable)
    {
        if(printLog)
            android.util.Log.i(s, s1, throwable);
    }

    public static void v(String s, String s1)
    {
        if(printLog)
            android.util.Log.v(s, s1);
    }

    public static void v(String s, String s1, Throwable throwable)
    {
        if(printLog)
            android.util.Log.v(s, s1, throwable);
    }

    public static void w(String s, String s1)
    {
        if(printLog)
            android.util.Log.w(s, s1);
    }

    public static void w(String s, String s1, Throwable throwable)
    {
        if(printLog)
            android.util.Log.w(s, s1, throwable);
    }

    public static void w(String s, Throwable throwable)
    {
        if(printLog)
            android.util.Log.w(s, throwable);
    }

    public static boolean D;
    public static boolean E;
    public static boolean I;
    public static boolean V;
    public static boolean W;
    private static boolean printLog;

    static 
    {
        printLog = Boolean.parseBoolean(Configuration.getProperty("printLog", "false"));
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if(printLog)
            flag = Boolean.parseBoolean(Configuration.getProperty("debugLog", "false"));
        else
            flag = false;
        D = flag;
        if(printLog)
            flag1 = Boolean.parseBoolean(Configuration.getProperty("viewLog", "false"));
        else
            flag1 = false;
        V = flag1;
        if(printLog)
            flag2 = Boolean.parseBoolean(Configuration.getProperty("infoLog", "false"));
        else
            flag2 = false;
        I = flag2;
        if(printLog)
            flag3 = Boolean.parseBoolean(Configuration.getProperty("warnLog", "false"));
        else
            flag3 = false;
        W = flag3;
        if(printLog)
            flag4 = Boolean.parseBoolean(Configuration.getProperty("errorLog", "false"));
        else
            flag4 = false;
        E = flag4;
    }
}
