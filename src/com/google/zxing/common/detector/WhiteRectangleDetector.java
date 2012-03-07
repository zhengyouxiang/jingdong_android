// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{

    public WhiteRectangleDetector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
        height = bitmatrix.getHeight();
        width = bitmatrix.getWidth();
    }

    private ResultPoint[] centerEdges(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
    {
        float f = resultpoint.getX();
        float f1 = resultpoint.getY();
        float f2 = resultpoint1.getX();
        float f3 = resultpoint1.getY();
        float f4 = resultpoint2.getX();
        float f5 = resultpoint2.getY();
        float f6 = resultpoint3.getX();
        float f7 = resultpoint3.getY();
        ResultPoint aresultpoint1[];
        if(f < (float)(width / 2))
        {
            ResultPoint aresultpoint2[] = new ResultPoint[4];
            aresultpoint2[0] = new ResultPoint(f6 - 1.0F, f7 + 1.0F);
            aresultpoint2[1] = new ResultPoint(f2 + 1.0F, f3 + 1.0F);
            aresultpoint2[2] = new ResultPoint(f4 - 1.0F, f5 - 1.0F);
            aresultpoint2[3] = new ResultPoint(f + 1.0F, f1 - 1.0F);
            aresultpoint1 = aresultpoint2;
        } else
        {
            ResultPoint aresultpoint[] = new ResultPoint[4];
            aresultpoint[0] = new ResultPoint(f6 + 1.0F, f7 + 1.0F);
            aresultpoint[1] = new ResultPoint(f2 + 1.0F, f3 - 1.0F);
            aresultpoint[2] = new ResultPoint(f4 - 1.0F, f5 + 1.0F);
            aresultpoint[3] = new ResultPoint(f - 1.0F, f1 - 1.0F);
            aresultpoint1 = aresultpoint;
        }
        return aresultpoint1;
    }

    private boolean containsBlackPoint(int i, int j, int k, boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        int i1 = i;
_L5:
        if(i1 > j)
            break MISSING_BLOCK_LABEL_73;
        if(!image.get(i1, k)) goto _L4; else goto _L3
_L3:
        boolean flag1 = true;
_L6:
        return flag1;
_L4:
        i1++;
          goto _L5
_L2:
        int l = i;
_L7:
label0:
        {
            if(l > j)
                break MISSING_BLOCK_LABEL_73;
            if(!image.get(k, l))
                break label0;
            flag1 = true;
        }
          goto _L6
        l++;
          goto _L7
        flag1 = false;
          goto _L6
    }

    private static int distanceL2(float f, float f1, float f2, float f3)
    {
        float f4 = f - f2;
        float f5 = f1 - f3;
        return round((float)Math.sqrt(f4 * f4 + f5 * f5));
    }

    private ResultPoint getBlackPointOnSegment(float f, float f1, float f2, float f3)
    {
        int i;
        float f4;
        float f5;
        int j;
        i = distanceL2(f, f1, f2, f3);
        f4 = (f2 - f) / (float)i;
        f5 = (f3 - f1) / (float)i;
        j = 0;
_L3:
        int k;
        int l;
        if(j >= i)
            break MISSING_BLOCK_LABEL_103;
        k = round(f + f4 * (float)j);
        l = round(f1 + f5 * (float)j);
        if(!image.get(k, l)) goto _L2; else goto _L1
_L1:
        ResultPoint resultpoint = new ResultPoint(k, l);
_L4:
        return resultpoint;
_L2:
        j++;
          goto _L3
        resultpoint = null;
          goto _L4
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    public ResultPoint[] detect()
        throws NotFoundException
    {
        int i1;
        int j1;
        boolean flag;
        boolean flag1;
        int k1;
        int l1;
        int i = width - 40 >> 1;
        int j = 40 + width >> 1;
        int k = height - 40 >> 1;
        int l = 40 + height >> 1;
        i1 = j;
        j1 = i;
        flag = false;
        flag1 = true;
        k1 = k;
        l1 = l;
        break MISSING_BLOCK_LABEL_60;
        if(flag7)
            flag = true;
        flag1 = flag7;
        l1 = k3;
        i1 = j3;
          goto _L1
        do
        {
            {
                if(j2 >= i2)
                    break MISSING_BLOCK_LABEL_651;
                resultpoint = getBlackPointOnSegment(j1, l1 - j2, j1 + j2, l1);
                boolean flag5;
                boolean flag6;
                int k3;
                boolean flag7;
                boolean flag8;
                boolean flag9;
                if(resultpoint != null)
                {
                    resultpoint1 = resultpoint;
                    break MISSING_BLOCK_LABEL_195;
                }
                j2++;
            }
        } while(true);
_L8:
        if(resultpoint5 == null)
            throw NotFoundException.getNotFoundInstance();
        i3 = 1;
        resultpoint6 = null;
_L6:
        if(i3 >= i2) goto _L3; else goto _L2
_L2:
        resultpoint6 = getBlackPointOnSegment(i1, l1 - i3, i1 - i3, l1);
        if(resultpoint6 == null) goto _L5; else goto _L4
_L4:
        resultpoint7 = resultpoint6;
_L7:
        if(resultpoint7 == null)
            throw NotFoundException.getNotFoundInstance();
        else
            return centerEdges(resultpoint7, resultpoint1, resultpoint5, resultpoint3);
_L5:
        i3++;
          goto _L6
_L1:
        if(!flag1)
            break MISSING_BLOCK_LABEL_658;
        boolean flag3 = true;
        int j3 = i1;
        boolean flag4 = false;
        do
        {
            if(!flag3 || j3 >= width)
                break;
            flag3 = containsBlackPoint(k1, l1, j3, false);
            if(flag3)
            {
                j3++;
                flag4 = true;
            }
        } while(true);
        boolean flag2;
        int i2;
        int j2;
        ResultPoint resultpoint;
        ResultPoint resultpoint1;
        if(j3 >= width)
        {
            flag2 = true;
            i1 = j3;
        } else
        {
            flag5 = true;
            flag6 = flag4;
            k3 = l1;
            flag7 = flag6;
            do
            {
                if(!flag5 || k3 >= height)
                    break;
                flag5 = containsBlackPoint(j1, j3, k3, true);
                if(flag5)
                {
                    k3++;
                    flag7 = true;
                }
            } while(true);
            if(k3 >= height)
            {
                flag2 = true;
                l1 = k3;
                i1 = j3;
            } else
            {
                flag8 = true;
                do
                {
                    if(!flag8 || j1 < 0)
                        break;
                    flag8 = containsBlackPoint(k1, k3, j1, false);
                    if(flag8)
                    {
                        j1--;
                        flag7 = true;
                    }
                } while(true);
                if(j1 < 0)
                {
                    flag2 = true;
                    l1 = k3;
                    i1 = j3;
                } else
                {
label0:
                    {
                        flag9 = true;
                        do
                        {
                            if(!flag9 || k1 < 0)
                                break;
                            flag9 = containsBlackPoint(j1, j3, k1, true);
                            if(flag9)
                            {
                                k1--;
                                flag7 = true;
                            }
                        } while(true);
                        if(k1 >= 0)
                            break label0;
                        flag2 = true;
                        l1 = k3;
                        i1 = j3;
                    }
                }
            }
        }
        int k2;
        ResultPoint resultpoint2;
        ResultPoint resultpoint3;
        int l2;
        ResultPoint resultpoint4;
        ResultPoint resultpoint5;
        int i3;
        ResultPoint resultpoint6;
        ResultPoint resultpoint7;
        for(; !flag2 && flag; flag2 = false)
        {
            i2 = i1 - j1;
            j2 = 1;
            resultpoint = null;
            break MISSING_BLOCK_LABEL_155;
        }

        throw NotFoundException.getNotFoundInstance();
_L3:
        resultpoint7 = resultpoint6;
          goto _L7
_L10:
        if(resultpoint1 == null)
            throw NotFoundException.getNotFoundInstance();
        k2 = 1;
        resultpoint2 = null;
        for(; k2 < i2; k2++)
        {
            resultpoint2 = getBlackPointOnSegment(j1, k1 + k2, j1 + k2, k1);
            if(resultpoint2 != null)
            {
                resultpoint3 = resultpoint2;
                break MISSING_BLOCK_LABEL_476;
            }
        }

        resultpoint3 = resultpoint2;
        if(resultpoint3 == null)
            throw NotFoundException.getNotFoundInstance();
        l2 = 1;
        resultpoint4 = null;
        for(; l2 < i2; l2++)
        {
            resultpoint4 = getBlackPointOnSegment(i1, k1 + l2, i1 - l2, k1);
            if(resultpoint4 != null)
            {
                resultpoint5 = resultpoint4;
                break MISSING_BLOCK_LABEL_537;
            }
        }

        resultpoint5 = resultpoint4;
          goto _L8
        resultpoint1 = resultpoint;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final int CORR = 1;
    private static final int INIT_SIZE = 40;
    private final int height;
    private final BitMatrix image;
    private final int width;
}
