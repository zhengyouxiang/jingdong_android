// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.*;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix, BitArray

public class GlobalHistogramBinarizer extends Binarizer
{

    public GlobalHistogramBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
        luminances = null;
        buckets = null;
    }

    private static int estimateBlackPoint(int ai[])
        throws NotFoundException
    {
        int i = ai.length;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for(; j < i; j++)
        {
            if(ai[j] > k)
            {
                k = ai[j];
                l = j;
            }
            if(ai[j] > i1)
                i1 = ai[j];
        }

        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        for(; j1 < i; j1++)
        {
            int j4 = j1 - l;
            int k4 = j4 * (j4 * ai[j1]);
            if(k4 > k1)
            {
                k1 = k4;
                l1 = j1;
            }
        }

        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        if(l > l1)
        {
            i2 = l;
            j2 = l1;
        } else
        {
            i2 = l1;
            j2 = l;
        }
        if(i2 - j2 <= i >> 4)
            throw NotFoundException.getNotFoundInstance();
        k2 = i2 - 1;
        l2 = -1;
        i3 = i2 - 1;
        j3 = k2;
        for(int k3 = i3; k3 > j2; k3--)
        {
            int l3 = k3 - j2;
            int i4 = l3 * l3 * (i2 - k3) * (i1 - ai[k3]);
            if(i4 > l2)
            {
                l2 = i4;
                j3 = k3;
            }
        }

        return j3 << 3;
    }

    private void initArrays(int i)
    {
        if(luminances == null || luminances.length < i)
            luminances = new byte[i];
        if(buckets == null)
        {
            buckets = new int[32];
        } else
        {
            int j = 0;
            while(j < 32) 
            {
                buckets[j] = 0;
                j++;
            }
        }
    }

    public Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new GlobalHistogramBinarizer(luminancesource);
    }

    public BitMatrix getBlackMatrix()
        throws NotFoundException
    {
        LuminanceSource luminancesource = getLuminanceSource();
        int i = luminancesource.getWidth();
        int j = luminancesource.getHeight();
        BitMatrix bitmatrix = new BitMatrix(i, j);
        initArrays(i);
        int ai[] = buckets;
        for(int k = 1; k < 5; k++)
        {
            byte abyte1[] = luminancesource.getRow((j * k) / 5, luminances);
            int l1 = (i << 2) / 5;
            for(int i2 = i / 5; i2 < l1; i2++)
            {
                int j2 = (0xff & abyte1[i2]) >> 3;
                ai[j2] = 1 + ai[j2];
            }

        }

        int l = estimateBlackPoint(ai);
        byte abyte0[] = luminancesource.getMatrix();
        for(int i1 = 0; i1 < j; i1++)
        {
            int j1 = i1 * i;
            for(int k1 = 0; k1 < i; k1++)
                if((0xff & abyte0[j1 + k1]) < l)
                    bitmatrix.set(k1, i1);

        }

        return bitmatrix;
    }

    public BitArray getBlackRow(int i, BitArray bitarray)
        throws NotFoundException
    {
        LuminanceSource luminancesource = getLuminanceSource();
        int j = luminancesource.getWidth();
        BitArray bitarray1;
        byte abyte0[];
        int ai[];
        if(bitarray == null || bitarray.getSize() < j)
        {
            bitarray1 = new BitArray(j);
        } else
        {
            bitarray.clear();
            bitarray1 = bitarray;
        }
        initArrays(j);
        abyte0 = luminancesource.getRow(i, luminances);
        ai = buckets;
        for(int k = 0; k < j; k++)
        {
            int j2 = (0xff & abyte0[k]) >> 3;
            ai[j2] = 1 + ai[j2];
        }

        int l = estimateBlackPoint(ai);
        int i1 = 0xff & abyte0[0];
        int j1 = 0xff & abyte0[1];
        int k1 = i1;
        for(int l1 = 1; l1 < j - 1;)
        {
            int i2 = 0xff & abyte0[l1 + 1];
            if((j1 << 2) - k1 - i2 >> 1 < l)
                bitarray1.set(l1);
            l1++;
            k1 = j1;
            j1 = i2;
        }

        return bitarray1;
    }

    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private int buckets[];
    private byte luminances[];
}
