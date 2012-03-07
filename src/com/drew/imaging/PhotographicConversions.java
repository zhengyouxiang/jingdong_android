// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.imaging;


public class PhotographicConversions
{

    private PhotographicConversions()
    {
    }

    public static double apertureToFStop(double d)
    {
        return Math.pow(ROOT_TWO, d);
    }

    public static double shutterSpeedToExposureTime(double d)
    {
        return (double)(float)(1.0D / Math.exp(d * Math.log(2D)));
    }

    public static final double ROOT_TWO = Math.sqrt(2D);

}
