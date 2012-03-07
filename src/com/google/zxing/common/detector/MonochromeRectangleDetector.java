// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector
{

    public MonochromeRectangleDetector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
    }

    private int[] blackWhiteRange(int i, int j, int k, int l, boolean flag)
    {
        int i1;
        i1 = k + l >> 1;
_L8:
        int j1;
        for(j1 = i1; j1 >= k;)
            if(flag ? image.get(j1, i) : image.get(i, j1))
                j1--;
            else
                break MISSING_BLOCK_LABEL_55;

          goto _L1
        int k1;
        int k2;
        int l2;
        for(k2 = j1; --k2 >= k && (flag ? !image.get(k2, i) : !image.get(i, k2)););
        l2 = j1 - k2;
        if(k2 >= k && l2 <= j) goto _L2; else goto _L1
_L1:
        k1 = j1 + 1;
_L7:
        if(i1 >= l) goto _L4; else goto _L3
_L3:
        if(flag ? image.get(i1, i) : image.get(i, i1)) goto _L6; else goto _L5
_L6:
        i1++;
          goto _L7
_L2:
        j1 = k2;
          goto _L8
_L5:
        int ai[];
        int i2;
        int l1;
        int ai1[];
        int j2;
        for(i2 = i1; ++i2 < l && (flag ? !image.get(i2, i) : !image.get(i, i2)););
        j2 = i2 - i1;
        if(i2 < l && j2 <= j)
            break MISSING_BLOCK_LABEL_283;
_L4:
        l1 = i1 - 1;
        if(l1 > k1)
        {
            ai1 = new int[2];
            ai1[0] = k1;
            ai1[1] = l1;
            ai = ai1;
        } else
        {
            ai = null;
        }
        return ai;
        i1 = i2;
          goto _L7
    }

    private ResultPoint findCornerFromCenter(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2)
        throws NotFoundException
    {
        int j2 = i;
        int k2 = i1;
        int ai1[];
        for(int ai[] = null; k2 < l1 && k2 >= k1 && j2 < l && j2 >= k; ai = ai1)
        {
            if(j == 0)
                ai1 = blackWhiteRange(k2, i2, k, l, true);
            else
                ai1 = blackWhiteRange(j2, i2, k1, l1, false);
            if(ai1 == null)
            {
                if(ai == null)
                    throw NotFoundException.getNotFoundInstance();
                ResultPoint resultpoint;
                if(j == 0)
                {
                    int i3 = k2 - j1;
                    if(ai[0] < i)
                    {
                        if(ai[1] > i)
                        {
                            float f2;
                            if(j1 > 0)
                                f2 = ai[0];
                            else
                                f2 = ai[1];
                            resultpoint = new ResultPoint(f2, i3);
                        } else
                        {
                            resultpoint = new ResultPoint(ai[0], i3);
                        }
                    } else
                    {
                        resultpoint = new ResultPoint(ai[1], i3);
                    }
                } else
                {
                    int l2 = j2 - j;
                    if(ai[0] < i1)
                    {
                        if(ai[1] > i1)
                        {
                            float f = l2;
                            float f1;
                            if(j < 0)
                                f1 = ai[0];
                            else
                                f1 = ai[1];
                            resultpoint = new ResultPoint(f, f1);
                        } else
                        {
                            resultpoint = new ResultPoint(l2, ai[0]);
                        }
                    } else
                    {
                        resultpoint = new ResultPoint(l2, ai[1]);
                    }
                }
                return resultpoint;
            }
            k2 += j1;
            j2 += j;
        }

        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect()
        throws NotFoundException
    {
        int i = image.getHeight();
        int j = image.getWidth();
        int k = i >> 1;
        int l = j >> 1;
        int i1 = Math.max(1, i / 256);
        int j1 = Math.max(1, j / 256);
        int k1 = (int)findCornerFromCenter(l, 0, 0, j, k, -i1, 0, i, l >> 1).getY() - 1;
        ResultPoint resultpoint = findCornerFromCenter(l, -j1, 0, j, k, 0, k1, i, k >> 1);
        int l1 = (int)resultpoint.getX() - 1;
        ResultPoint resultpoint1 = findCornerFromCenter(l, j1, l1, j, k, 0, k1, i, k >> 1);
        int i2 = 1 + (int)resultpoint1.getX();
        ResultPoint resultpoint2 = findCornerFromCenter(l, 0, l1, i2, k, i1, k1, i, l >> 1);
        int j2 = 1 + (int)resultpoint2.getY();
        ResultPoint resultpoint3 = findCornerFromCenter(l, 0, l1, i2, k, -i1, k1, j2, l >> 2);
        ResultPoint aresultpoint[] = new ResultPoint[4];
        aresultpoint[0] = resultpoint3;
        aresultpoint[1] = resultpoint;
        aresultpoint[2] = resultpoint1;
        aresultpoint[3] = resultpoint2;
        return aresultpoint;
    }

    private static final int MAX_MODULES = 32;
    private final BitMatrix image;
}
