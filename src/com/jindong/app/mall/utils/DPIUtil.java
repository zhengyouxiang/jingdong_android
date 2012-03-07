// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.view.Display;

public class DPIUtil
{

    public DPIUtil()
    {
    }

    public static int dip2px(float f)
    {
        return (int)(0.5F + f * mDensity);
    }

    public static int getHeight()
    {
        return defaultDisplay.getHeight();
    }

    public static int getWidth()
    {
        return defaultDisplay.getWidth();
    }

    public static int percentHeight(float f)
    {
        return (int)(f * (float)defaultDisplay.getHeight());
    }

    public static int percentWidth(float f)
    {
        return (int)(f * (float)defaultDisplay.getWidth());
    }

    public static int px2dip(Context context, float f)
    {
        return (int)(0.5F + f / mDensity);
    }

    public static void setDefaultDisplay(Display display)
    {
        defaultDisplay = display;
    }

    public static void setDensity(float f)
    {
        mDensity = f;
    }

    private static Display defaultDisplay;
    private static float mDensity;
}
