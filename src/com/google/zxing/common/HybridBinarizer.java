// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.*;
import java.lang.reflect.Array;

// Referenced classes of package com.google.zxing.common:
//            GlobalHistogramBinarizer, BitMatrix

public final class HybridBinarizer extends GlobalHistogramBinarizer
{

    public HybridBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
        matrix = null;
    }

    private void binarizeEntireImage()
        throws NotFoundException
    {
        if(matrix == null)
        {
            LuminanceSource luminancesource = getLuminanceSource();
            if(luminancesource.getWidth() >= 40 && luminancesource.getHeight() >= 40)
            {
                byte abyte0[] = luminancesource.getMatrix();
                int i = luminancesource.getWidth();
                int j = luminancesource.getHeight();
                int k = i >> 3;
                if((i & 7) != 0)
                    k++;
                int l = j >> 3;
                if((j & 7) != 0)
                    l++;
                int ai[][] = calculateBlackPoints(abyte0, k, l, i, j);
                matrix = new BitMatrix(i, j);
                calculateThresholdForBlock(abyte0, k, l, i, j, ai, matrix);
            } else
            {
                matrix = super.getBlackMatrix();
            }
        }
    }

    private static int[][] calculateBlackPoints(byte abyte0[], int i, int j, int k, int l)
    {
        int ai[] = new int[2];
        ai[0] = j;
        ai[1] = i;
        int ai1[][] = (int[][])Array.newInstance(Integer.TYPE, ai);
        for(int i1 = 0; i1 < j; i1++)
        {
            int j1 = i1 << 3;
            if(j1 + 8 >= l)
                j1 = l - 8;
            int k1 = 0;
            while(k1 < i) 
            {
                int l1 = k1 << 3;
                if(l1 + 8 >= k)
                    l1 = k - 8;
                int i2 = 0;
                int j2 = 0;
                int k2 = 0;
                int l2 = 255;
                while(j2 < 8) 
                {
                    int j3 = l1 + k * (j1 + j2);
                    int k3 = i2;
                    int l3 = l2;
                    int i4 = k2;
                    for(int j4 = 0; j4 < 8; j4++)
                    {
                        int k4 = 0xff & abyte0[j3 + j4];
                        k3 += k4;
                        if(k4 < l3)
                            l3 = k4;
                        if(k4 > i4)
                            i4 = k4;
                    }

                    j2++;
                    k2 = i4;
                    l2 = l3;
                    i2 = k3;
                }
                int i3;
                if(k2 - l2 > 24)
                    i3 = i2 >> 6;
                else
                if(k2 == 0)
                    i3 = 1;
                else
                    i3 = l2 >> 1;
                ai1[i1][k1] = i3;
                k1++;
            }
        }

        return ai1;
    }

    private static void calculateThresholdForBlock(byte abyte0[], int i, int j, int k, int l, int ai[][], BitMatrix bitmatrix)
    {
        int i1 = 0;
        while(i1 < j) 
        {
            int j1 = i1 << 3;
            int k1;
            int l1;
            if(j1 + 8 >= l)
                k1 = l - 8;
            else
                k1 = j1;
            l1 = 0;
            while(l1 < i) 
            {
                int i2 = l1 << 3;
                int j2;
                int k2;
                int l2;
                int i3;
                if(i2 + 8 >= k)
                    j2 = k - 8;
                else
                    j2 = i2;
                if(l1 > 1)
                    k2 = l1;
                else
                    k2 = 2;
                if(k2 >= i - 2)
                    k2 = i - 3;
                if(i1 > 1)
                    l2 = i1;
                else
                    l2 = 2;
                if(l2 >= j - 2)
                    l2 = j - 3;
                i3 = 0;
                for(int j3 = -2; j3 <= 2; j3++)
                {
                    int ai1[] = ai[l2 + j3];
                    i3 = i3 + ai1[k2 - 2] + ai1[k2 - 1] + ai1[k2] + ai1[k2 + 1] + ai1[k2 + 2];
                }

                threshold8x8Block(abyte0, j2, k1, i3 / 25, k, bitmatrix);
                l1++;
            }
            i1++;
        }
    }

    private static void threshold8x8Block(byte abyte0[], int i, int j, int k, int l, BitMatrix bitmatrix)
    {
        for(int i1 = 0; i1 < 8; i1++)
        {
            int j1 = i + l * (j + i1);
            for(int k1 = 0; k1 < 8; k1++)
                if((0xff & abyte0[j1 + k1]) < k)
                    bitmatrix.set(i + k1, j + i1);

        }

    }

    public Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new HybridBinarizer(luminancesource);
    }

    public BitMatrix getBlackMatrix()
        throws NotFoundException
    {
        binarizeEntireImage();
        return matrix;
    }

    private static final int MINIMUM_DIMENSION = 40;
    private BitMatrix matrix;
}
