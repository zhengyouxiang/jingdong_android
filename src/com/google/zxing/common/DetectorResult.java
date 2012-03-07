// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix

public final class DetectorResult
{

    public DetectorResult(BitMatrix bitmatrix, ResultPoint aresultpoint[])
    {
        bits = bitmatrix;
        points = aresultpoint;
    }

    public BitMatrix getBits()
    {
        return bits;
    }

    public ResultPoint[] getPoints()
    {
        return points;
    }

    private final BitMatrix bits;
    private final ResultPoint points[];
}
