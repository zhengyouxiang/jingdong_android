// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.pdf417.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import java.util.Hashtable;

public final class Detector
{

    public Detector(BinaryBitmap binarybitmap)
    {
        image = binarybitmap;
    }

    private static int computeDimension(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, float f)
    {
        return 17 * ((8 + (round(ResultPoint.distance(resultpoint, resultpoint1) / f) + round(ResultPoint.distance(resultpoint2, resultpoint3) / f) >> 1)) / 17);
    }

    private static float computeModuleWidth(ResultPoint aresultpoint[])
    {
        return ((ResultPoint.distance(aresultpoint[0], aresultpoint[4]) + ResultPoint.distance(aresultpoint[1], aresultpoint[5])) / 34F + (ResultPoint.distance(aresultpoint[6], aresultpoint[2]) + ResultPoint.distance(aresultpoint[7], aresultpoint[3])) / 36F) / 2.0F;
    }

    private static void correctCodeWordVertices(ResultPoint aresultpoint[], boolean flag)
    {
        float f = aresultpoint[4].getY() - aresultpoint[6].getY();
        if(flag)
            f = -f;
        float f4;
        if(f > 2.0F)
        {
            float f11 = aresultpoint[4].getX() - aresultpoint[0].getX();
            float f12 = aresultpoint[6].getX() - aresultpoint[0].getX();
            float f13 = (f11 * (aresultpoint[6].getY() - aresultpoint[0].getY())) / f12;
            aresultpoint[4] = new ResultPoint(aresultpoint[4].getX(), f13 + aresultpoint[4].getY());
        } else
        if(-f > 2.0F)
        {
            float f1 = aresultpoint[2].getX() - aresultpoint[6].getX();
            float f2 = aresultpoint[2].getX() - aresultpoint[4].getX();
            float f3 = (f1 * (aresultpoint[2].getY() - aresultpoint[4].getY())) / f2;
            aresultpoint[6] = new ResultPoint(aresultpoint[6].getX(), aresultpoint[6].getY() - f3);
        }
        f4 = aresultpoint[7].getY() - aresultpoint[5].getY();
        if(flag)
            f4 = -f4;
        if(f4 > 2.0F)
        {
            float f8 = aresultpoint[5].getX() - aresultpoint[1].getX();
            float f9 = aresultpoint[7].getX() - aresultpoint[1].getX();
            float f10 = (f8 * (aresultpoint[7].getY() - aresultpoint[1].getY())) / f9;
            aresultpoint[5] = new ResultPoint(aresultpoint[5].getX(), f10 + aresultpoint[5].getY());
        } else
        if(-f4 > 2.0F)
        {
            float f5 = aresultpoint[3].getX() - aresultpoint[7].getX();
            float f6 = aresultpoint[3].getX() - aresultpoint[5].getX();
            float f7 = (f5 * (aresultpoint[3].getY() - aresultpoint[5].getY())) / f6;
            aresultpoint[7] = new ResultPoint(aresultpoint[7].getX(), aresultpoint[7].getY() - f7);
        }
    }

    private static int[] findGuardPattern(BitMatrix bitmatrix, int i, int j, int k, boolean flag, int ai[])
    {
        int l;
        int ai1[];
        int i1;
        boolean flag1;
        int j1;
        int k1;
        l = ai.length;
        ai1 = new int[l];
        i1 = 0;
        flag1 = flag;
        j1 = i;
        k1 = i;
_L2:
        if(j1 >= i + k)
            break MISSING_BLOCK_LABEL_197;
        if(!(flag1 ^ bitmatrix.get(j1, j)))
            break; /* Loop/switch isn't completed */
        ai1[i1] = 1 + ai1[i1];
_L7:
        j1++;
        if(true) goto _L2; else goto _L1
_L1:
        if(i1 != l - 1) goto _L4; else goto _L3
_L3:
        if(patternMatchVariance(ai1, ai, 204) >= 107) goto _L6; else goto _L5
_L5:
        int ai2[];
        ai2 = new int[2];
        ai2[0] = k1;
        ai2[1] = j1;
_L9:
        return ai2;
_L6:
        k1 += ai1[0] + ai1[1];
        for(int l1 = 2; l1 < l; l1++)
            ai1[l1 - 2] = ai1[l1];

        ai1[l - 2] = 0;
        ai1[l - 1] = 0;
        i1--;
_L8:
        ai1[i1] = 1;
        if(!flag1)
            flag1 = true;
        else
            flag1 = false;
          goto _L7
_L4:
        i1++;
          goto _L8
        ai2 = null;
          goto _L9
    }

    private static ResultPoint[] findVertices(BitMatrix bitmatrix)
    {
        int i;
        int j;
        ResultPoint aresultpoint[];
        int k;
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth();
        aresultpoint = new ResultPoint[8];
        k = 0;
_L21:
        int ai3[];
        if(k >= i)
            break MISSING_BLOCK_LABEL_362;
        ai3 = findGuardPattern(bitmatrix, 0, k, j, false, START_PATTERN);
        if(ai3 == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        aresultpoint[0] = new ResultPoint(ai3[0], k);
        aresultpoint[4] = new ResultPoint(ai3[1], k);
        flag = true;
_L25:
        if(!flag) goto _L4; else goto _L3
_L3:
        int j1 = i - 1;
_L22:
        if(j1 <= 0) goto _L6; else goto _L5
_L5:
        int ai2[] = findGuardPattern(bitmatrix, 0, j1, j, false, START_PATTERN);
        if(ai2 == null) goto _L8; else goto _L7
_L7:
        aresultpoint[1] = new ResultPoint(ai2[0], j1);
        aresultpoint[5] = new ResultPoint(ai2[1], j1);
        flag = true;
_L4:
        if(!flag) goto _L10; else goto _L9
_L9:
        int i1 = 0;
_L23:
        if(i1 >= i) goto _L12; else goto _L11
_L11:
        int ai1[] = findGuardPattern(bitmatrix, 0, i1, j, false, STOP_PATTERN);
        if(ai1 == null) goto _L14; else goto _L13
_L13:
        aresultpoint[2] = new ResultPoint(ai1[1], i1);
        aresultpoint[6] = new ResultPoint(ai1[0], i1);
        flag = true;
_L10:
        if(!flag) goto _L16; else goto _L15
_L15:
        int l = i - 1;
_L24:
        if(l <= 0) goto _L18; else goto _L17
_L17:
        int ai[] = findGuardPattern(bitmatrix, 0, l, j, false, STOP_PATTERN);
        if(ai == null) goto _L20; else goto _L19
_L19:
        aresultpoint[3] = new ResultPoint(ai[1], l);
        aresultpoint[7] = new ResultPoint(ai[0], l);
        flag = true;
_L16:
        ResultPoint aresultpoint1[];
        if(flag)
            aresultpoint1 = aresultpoint;
        else
            aresultpoint1 = null;
        return aresultpoint1;
_L2:
        k++;
          goto _L21
_L8:
        j1--;
          goto _L22
_L14:
        i1++;
          goto _L23
_L20:
        l--;
          goto _L24
_L18:
        flag = false;
          goto _L16
_L12:
        flag = false;
          goto _L10
_L6:
        flag = false;
          goto _L4
        flag = false;
          goto _L25
    }

    private static ResultPoint[] findVertices180(BitMatrix bitmatrix)
    {
        int i;
        int j;
        ResultPoint aresultpoint[];
        int k;
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth() >> 1;
        aresultpoint = new ResultPoint[8];
        k = i - 1;
_L21:
        int ai3[];
        if(k <= 0)
            break MISSING_BLOCK_LABEL_364;
        ai3 = findGuardPattern(bitmatrix, j, k, j, true, START_PATTERN_REVERSE);
        if(ai3 == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        aresultpoint[0] = new ResultPoint(ai3[1], k);
        aresultpoint[4] = new ResultPoint(ai3[0], k);
        flag = true;
_L25:
        if(!flag) goto _L4; else goto _L3
_L3:
        int j1 = 0;
_L22:
        if(j1 >= i) goto _L6; else goto _L5
_L5:
        int ai2[] = findGuardPattern(bitmatrix, j, j1, j, true, START_PATTERN_REVERSE);
        if(ai2 == null) goto _L8; else goto _L7
_L7:
        aresultpoint[1] = new ResultPoint(ai2[1], j1);
        aresultpoint[5] = new ResultPoint(ai2[0], j1);
        flag = true;
_L4:
        if(!flag) goto _L10; else goto _L9
_L9:
        int i1 = i - 1;
_L23:
        if(i1 <= 0) goto _L12; else goto _L11
_L11:
        int ai1[] = findGuardPattern(bitmatrix, 0, i1, j, false, STOP_PATTERN_REVERSE);
        if(ai1 == null) goto _L14; else goto _L13
_L13:
        aresultpoint[2] = new ResultPoint(ai1[0], i1);
        aresultpoint[6] = new ResultPoint(ai1[1], i1);
        flag = true;
_L10:
        if(!flag) goto _L16; else goto _L15
_L15:
        int l = 0;
_L24:
        if(l >= i) goto _L18; else goto _L17
_L17:
        int ai[] = findGuardPattern(bitmatrix, 0, l, j, false, STOP_PATTERN_REVERSE);
        if(ai == null) goto _L20; else goto _L19
_L19:
        aresultpoint[3] = new ResultPoint(ai[0], l);
        aresultpoint[7] = new ResultPoint(ai[1], l);
        flag = true;
_L16:
        ResultPoint aresultpoint1[];
        if(flag)
            aresultpoint1 = aresultpoint;
        else
            aresultpoint1 = null;
        return aresultpoint1;
_L2:
        k--;
          goto _L21
_L8:
        j1++;
          goto _L22
_L14:
        i1--;
          goto _L23
_L20:
        l++;
          goto _L24
_L18:
        flag = false;
          goto _L16
_L12:
        flag = false;
          goto _L10
_L6:
        flag = false;
          goto _L4
        flag = false;
          goto _L25
    }

    private static int patternMatchVariance(int ai[], int ai1[], int i)
    {
        int j;
        int k;
        int i1;
        int j1;
        j = 0;
        k = ai.length;
        int l = 0;
        i1 = 0;
        j1 = 0;
        for(; l < k; l++)
        {
            j1 += ai[l];
            i1 += ai1[l];
        }

        if(j1 >= i1) goto _L2; else goto _L1
_L1:
        int j2 = 0x7fffffff;
_L4:
        return j2;
_L2:
        int k1 = (j1 << 8) / i1;
        int l1 = i * k1 >> 8;
        int i2 = 0;
        do
        {
            if(i2 >= k)
                break;
            int k2 = ai[i2] << 8;
            int l2 = k1 * ai1[i2];
            int i3;
            if(k2 > l2)
                i3 = k2 - l2;
            else
                i3 = l2 - k2;
            if(i3 > l1)
            {
                j2 = 0x7fffffff;
                continue; /* Loop/switch isn't completed */
            }
            j += i3;
            i2++;
        } while(true);
        j2 = j / j1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
        throws NotFoundException
    {
        return GridSampler.getInstance().sampleGrid(bitmatrix, i, 0.0F, 0.0F, i, 0.0F, i, i, 0.0F, i, resultpoint.getX(), resultpoint.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint3.getX(), resultpoint3.getY(), resultpoint1.getX(), resultpoint1.getY());
    }

    public DetectorResult detect()
        throws NotFoundException
    {
        return detect(null);
    }

    public DetectorResult detect(Hashtable hashtable)
        throws NotFoundException
    {
        BitMatrix bitmatrix;
        ResultPoint aresultpoint[];
        bitmatrix = image.getBlackMatrix();
        aresultpoint = findVertices(bitmatrix);
        if(aresultpoint != null) goto _L2; else goto _L1
_L1:
        aresultpoint = findVertices180(bitmatrix);
        if(aresultpoint == null) goto _L4; else goto _L3
_L3:
        ResultPoint aresultpoint1[];
        correctCodeWordVertices(aresultpoint, true);
        aresultpoint1 = aresultpoint;
_L6:
        if(aresultpoint1 == null)
            throw NotFoundException.getNotFoundInstance();
        break; /* Loop/switch isn't completed */
_L2:
        correctCodeWordVertices(aresultpoint, false);
_L4:
        aresultpoint1 = aresultpoint;
        if(true) goto _L6; else goto _L5
_L5:
        float f = computeModuleWidth(aresultpoint1);
        if(f < 1.0F)
            throw NotFoundException.getNotFoundInstance();
        int i = computeDimension(aresultpoint1[4], aresultpoint1[6], aresultpoint1[5], aresultpoint1[7], f);
        if(i < 1)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            BitMatrix bitmatrix1 = sampleGrid(bitmatrix, aresultpoint1[4], aresultpoint1[5], aresultpoint1[6], aresultpoint1[7], i);
            ResultPoint aresultpoint2[] = new ResultPoint[4];
            aresultpoint2[0] = aresultpoint1[4];
            aresultpoint2[1] = aresultpoint1[5];
            aresultpoint2[2] = aresultpoint1[6];
            aresultpoint2[3] = aresultpoint1[7];
            return new DetectorResult(bitmatrix1, aresultpoint2);
        }
    }

    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 204;
    private static final int SKEW_THRESHOLD = 2;
    private static final int START_PATTERN[];
    private static final int START_PATTERN_REVERSE[];
    private static final int STOP_PATTERN[];
    private static final int STOP_PATTERN_REVERSE[];
    private final BinaryBitmap image;

    static 
    {
        int ai[] = new int[8];
        ai[0] = 8;
        ai[1] = 1;
        ai[2] = 1;
        ai[3] = 1;
        ai[4] = 1;
        ai[5] = 1;
        ai[6] = 1;
        ai[7] = 3;
        START_PATTERN = ai;
        int ai1[] = new int[8];
        ai1[0] = 3;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        ai1[4] = 1;
        ai1[5] = 1;
        ai1[6] = 1;
        ai1[7] = 8;
        START_PATTERN_REVERSE = ai1;
        int ai2[] = new int[9];
        ai2[0] = 7;
        ai2[1] = 1;
        ai2[2] = 1;
        ai2[3] = 3;
        ai2[4] = 1;
        ai2[5] = 1;
        ai2[6] = 1;
        ai2[7] = 2;
        ai2[8] = 1;
        STOP_PATTERN = ai2;
        int ai3[] = new int[9];
        ai3[0] = 1;
        ai3[1] = 2;
        ai3[2] = 1;
        ai3[3] = 1;
        ai3[4] = 1;
        ai3[5] = 3;
        ai3[6] = 1;
        ai3[7] = 1;
        ai3[8] = 7;
        STOP_PATTERN_REVERSE = ai3;
    }
}
