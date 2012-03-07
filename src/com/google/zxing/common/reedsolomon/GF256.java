// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GF256Poly

public final class GF256
{

    private GF256(int i)
    {
        int j = 0;
        int k = 1;
        for(; j < 256; j++)
        {
            expTable[j] = k;
            k <<= 1;
            if(k >= 256)
                k ^= i;
        }

        for(int l = 0; l < 255; l++)
            logTable[expTable[l]] = l;

        int ai[] = new int[1];
        ai[0] = 0;
        zero = new GF256Poly(this, ai);
        int ai1[] = new int[1];
        ai1[0] = 1;
        one = new GF256Poly(this, ai1);
    }

    static int addOrSubtract(int i, int j)
    {
        return i ^ j;
    }

    GF256Poly buildMonomial(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        GF256Poly gf256poly;
        if(j == 0)
        {
            gf256poly = zero;
        } else
        {
            int ai[] = new int[i + 1];
            ai[0] = j;
            gf256poly = new GF256Poly(this, ai);
        }
        return gf256poly;
    }

    int exp(int i)
    {
        return expTable[i];
    }

    GF256Poly getOne()
    {
        return one;
    }

    GF256Poly getZero()
    {
        return zero;
    }

    int inverse(int i)
    {
        if(i == 0)
            throw new ArithmeticException();
        else
            return expTable[255 - logTable[i]];
    }

    int log(int i)
    {
        if(i == 0)
            throw new IllegalArgumentException();
        else
            return logTable[i];
    }

    int multiply(int i, int j)
    {
        int k;
        if(i == 0 || j == 0)
        {
            k = 0;
        } else
        {
            int l = logTable[i] + logTable[j];
            k = expTable[(l & 0xff) + (l >>> 8)];
        }
        return k;
    }

    public static final GF256 DATA_MATRIX_FIELD = new GF256(301);
    public static final GF256 QR_CODE_FIELD = new GF256(285);
    private final int expTable[] = new int[256];
    private final int logTable[] = new int[256];
    private final GF256Poly one;
    private final GF256Poly zero;

}
