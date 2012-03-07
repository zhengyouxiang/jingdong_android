// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;


public class ResultPoint
{

    public ResultPoint(float f, float f1)
    {
        x = f;
        y = f1;
    }

    private static float crossProductZ(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2)
    {
        float f = resultpoint1.x;
        float f1 = resultpoint1.y;
        return (resultpoint2.x - f) * (resultpoint.y - f1) - (resultpoint2.y - f1) * (resultpoint.x - f);
    }

    public static float distance(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        float f = resultpoint.getX() - resultpoint1.getX();
        float f1 = resultpoint.getY() - resultpoint1.getY();
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    public static void orderBestPatterns(ResultPoint aresultpoint[])
    {
        float f = distance(aresultpoint[0], aresultpoint[1]);
        float f1 = distance(aresultpoint[1], aresultpoint[2]);
        float f2 = distance(aresultpoint[0], aresultpoint[2]);
        ResultPoint resultpoint3;
        ResultPoint resultpoint4;
        ResultPoint resultpoint5;
        if(f1 >= f && f1 >= f2)
        {
            ResultPoint resultpoint10 = aresultpoint[0];
            ResultPoint resultpoint11 = aresultpoint[1];
            ResultPoint resultpoint12 = aresultpoint[2];
            resultpoint3 = resultpoint11;
            resultpoint4 = resultpoint10;
            resultpoint5 = resultpoint12;
        } else
        if(f2 >= f1 && f2 >= f)
        {
            ResultPoint resultpoint7 = aresultpoint[1];
            ResultPoint resultpoint8 = aresultpoint[0];
            ResultPoint resultpoint9 = aresultpoint[2];
            resultpoint3 = resultpoint8;
            resultpoint4 = resultpoint7;
            resultpoint5 = resultpoint9;
        } else
        {
            ResultPoint resultpoint = aresultpoint[2];
            ResultPoint resultpoint1 = aresultpoint[0];
            ResultPoint resultpoint2 = aresultpoint[1];
            resultpoint3 = resultpoint1;
            resultpoint4 = resultpoint;
            resultpoint5 = resultpoint2;
        }
        if(crossProductZ(resultpoint3, resultpoint4, resultpoint5) < 0.0F)
        {
            ResultPoint resultpoint6 = resultpoint3;
            resultpoint3 = resultpoint5;
            resultpoint5 = resultpoint6;
        }
        aresultpoint[0] = resultpoint3;
        aresultpoint[1] = resultpoint4;
        aresultpoint[2] = resultpoint5;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof ResultPoint)
        {
            ResultPoint resultpoint = (ResultPoint)obj;
            if(x == resultpoint.x && y == resultpoint.y)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public final float getX()
    {
        return x;
    }

    public final float getY()
    {
        return y;
    }

    public int hashCode()
    {
        return 31 * Float.floatToIntBits(x) + Float.floatToIntBits(y);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(25);
        stringbuffer.append('(');
        stringbuffer.append(x);
        stringbuffer.append(',');
        stringbuffer.append(y);
        stringbuffer.append(')');
        return stringbuffer.toString();
    }

    private final float x;
    private final float y;
}
