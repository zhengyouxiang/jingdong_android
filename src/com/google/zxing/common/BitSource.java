// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


public final class BitSource
{

    public BitSource(byte abyte0[])
    {
        bytes = abyte0;
    }

    public int available()
    {
        return 8 * (bytes.length - byteOffset) - bitOffset;
    }

    public int readBits(int i)
    {
        if(i < 1 || i > 32)
            throw new IllegalArgumentException();
        int j;
        int k;
        if(bitOffset > 0)
        {
            int j1 = 8 - bitOffset;
            int k1;
            int l1;
            int i2;
            if(i < j1)
                k1 = i;
            else
                k1 = j1;
            l1 = j1 - k1;
            j = ((255 >> 8 - k1) << l1 & bytes[byteOffset]) >> l1;
            i2 = i - k1;
            bitOffset = k1 + bitOffset;
            if(bitOffset == 8)
            {
                bitOffset = 0;
                byteOffset = 1 + byteOffset;
            }
            k = i2;
        } else
        {
            j = 0;
            k = i;
        }
        if(k > 0)
        {
            for(; k >= 8; k -= 8)
            {
                j = j << 8 | 0xff & bytes[byteOffset];
                byteOffset = 1 + byteOffset;
            }

            if(k > 0)
            {
                int l = 8 - k;
                int i1 = (255 >> l) << l;
                j = j << k | (i1 & bytes[byteOffset]) >> l;
                bitOffset = k + bitOffset;
            }
        }
        return j;
    }

    private int bitOffset;
    private int byteOffset;
    private final byte bytes[];
}
