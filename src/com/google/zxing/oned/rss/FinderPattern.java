// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern
{

    public FinderPattern(int i, int ai[], int j, int k, int l)
    {
        value = i;
        startEnd = ai;
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(j, l);
        aresultpoint[1] = new ResultPoint(k, l);
        resultPoints = aresultpoint;
    }

    public ResultPoint[] getResultPoints()
    {
        return resultPoints;
    }

    public int[] getStartEnd()
    {
        return startEnd;
    }

    public int getValue()
    {
        return value;
    }

    private final ResultPoint resultPoints[];
    private final int startEnd[];
    private final int value;
}
