// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;


public class DataCharacter
{

    public DataCharacter(int i, int j)
    {
        value = i;
        checksumPortion = j;
    }

    public int getChecksumPortion()
    {
        return checksumPortion;
    }

    public int getValue()
    {
        return value;
    }

    private final int checksumPortion;
    private final int value;
}
