// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.*;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.util.*;

public final class Detector
{
    private static class ResultPointsAndTransitionsComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return ((ResultPointsAndTransitions)obj).getTransitions() - ((ResultPointsAndTransitions)obj1).getTransitions();
        }

        private ResultPointsAndTransitionsComparator()
        {
        }

    }

    private static class ResultPointsAndTransitions
    {

        public ResultPoint getFrom()
        {
            return from;
        }

        public ResultPoint getTo()
        {
            return to;
        }

        public int getTransitions()
        {
            return transitions;
        }

        public String toString()
        {
            return from + "/" + to + '/' + transitions;
        }

        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint resultpoint, ResultPoint resultpoint1, int i)
        {
            from = resultpoint;
            to = resultpoint1;
            transitions = i;
        }

    }


    public Detector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
        rectangleDetector = new WhiteRectangleDetector(bitmatrix);
    }

    private ResultPoint correctTopRight(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
    {
        float f = (float)distance(resultpoint, resultpoint1) / (float)i;
        int j = distance(resultpoint2, resultpoint3);
        float f1 = (resultpoint3.getX() - resultpoint2.getX()) / (float)j;
        float f2 = (resultpoint3.getY() - resultpoint2.getY()) / (float)j;
        ResultPoint resultpoint4 = new ResultPoint(resultpoint3.getX() + f1 * f, resultpoint3.getY() + f * f2);
        float f3 = (float)distance(resultpoint, resultpoint1) / (float)i;
        int k = distance(resultpoint1, resultpoint3);
        float f4 = (resultpoint3.getX() - resultpoint1.getX()) / (float)k;
        float f5 = (resultpoint3.getY() - resultpoint1.getY()) / (float)k;
        ResultPoint resultpoint5 = new ResultPoint(resultpoint3.getX() + f4 * f3, resultpoint3.getY() + f3 * f5);
        ResultPoint resultpoint6;
        if(!isValid(resultpoint4))
        {
            if(isValid(resultpoint5))
                resultpoint6 = resultpoint5;
            else
                resultpoint6 = null;
        } else
        if(!isValid(resultpoint5))
            resultpoint6 = resultpoint4;
        else
        if(Math.abs(transitionsBetween(resultpoint2, resultpoint4).getTransitions() - transitionsBetween(resultpoint1, resultpoint4).getTransitions()) <= Math.abs(transitionsBetween(resultpoint2, resultpoint5).getTransitions() - transitionsBetween(resultpoint1, resultpoint5).getTransitions()))
            resultpoint6 = resultpoint4;
        else
            resultpoint6 = resultpoint5;
        return resultpoint6;
    }

    private static int distance(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        return round((float)Math.sqrt((resultpoint.getX() - resultpoint1.getX()) * (resultpoint.getX() - resultpoint1.getX()) + (resultpoint.getY() - resultpoint1.getY()) * (resultpoint.getY() - resultpoint1.getY())));
    }

    private static void increment(Hashtable hashtable, ResultPoint resultpoint)
    {
        Integer integer = (Integer)hashtable.get(resultpoint);
        Integer integer1;
        if(integer == null)
            integer1 = INTEGERS[1];
        else
            integer1 = INTEGERS[1 + integer.intValue()];
        hashtable.put(resultpoint, integer1);
    }

    private boolean isValid(ResultPoint resultpoint)
    {
        boolean flag;
        if(resultpoint.getX() >= 0.0F && resultpoint.getX() < (float)image.width && resultpoint.getY() > 0.0F && resultpoint.getY() < (float)image.height)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
        throws NotFoundException
    {
        return GridSampler.getInstance().sampleGrid(bitmatrix, i, 0.5F, 0.5F, (float)i - 0.5F, 0.5F, (float)i - 0.5F, (float)i - 0.5F, 0.5F, (float)i - 0.5F, resultpoint.getX(), resultpoint.getY(), resultpoint3.getX(), resultpoint3.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint1.getX(), resultpoint1.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        int i = (int)resultpoint.getX();
        int j = (int)resultpoint.getY();
        int k = (int)resultpoint1.getX();
        int l = (int)resultpoint1.getY();
        boolean flag;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        byte byte0;
        byte byte1;
        int l2;
        BitMatrix bitmatrix;
        int i3;
        int j3;
        boolean flag1;
        int k3;
        boolean flag2;
        int l3;
        ResultPointsAndTransitions resultpointsandtransitions;
        BitMatrix bitmatrix1;
        int i4;
        int j4;
        boolean flag3;
        if(Math.abs(l - j) > Math.abs(k - i))
            flag = true;
        else
            flag = false;
        if(flag)
        {
            l1 = i;
            j1 = k;
            i1 = j;
            k1 = l;
        } else
        {
            i1 = i;
            j1 = l;
            k1 = k;
            l1 = j;
        }
        i2 = Math.abs(k1 - i1);
        j2 = Math.abs(j1 - l1);
        k2 = -i2 >> 1;
        if(l1 < j1)
            byte0 = 1;
        else
            byte0 = -1;
        if(i1 < k1)
            byte1 = 1;
        else
            byte1 = -1;
        l2 = 0;
        bitmatrix = image;
        if(flag)
            i3 = l1;
        else
            i3 = i1;
        if(flag)
            j3 = i1;
        else
            j3 = l1;
        flag1 = bitmatrix.get(i3, j3);
        k3 = k2;
        flag2 = flag1;
        if(i1 == k1) goto _L2; else goto _L1
_L1:
        bitmatrix1 = image;
        if(flag)
            i4 = l1;
        else
            i4 = i1;
        if(flag)
            j4 = i1;
        else
            j4 = l1;
        flag3 = bitmatrix1.get(i4, j4);
        if(flag3 != flag2)
        {
            l2++;
            flag2 = flag3;
        }
        k3 += j2;
        if(k3 <= 0)
            continue; /* Loop/switch isn't completed */
        if(l1 != j1) goto _L4; else goto _L3
_L3:
        l3 = l2;
_L6:
        resultpointsandtransitions = new ResultPointsAndTransitions(resultpoint, resultpoint1, l3);
        return resultpointsandtransitions;
_L4:
        l1 += byte0;
        k3 -= i2;
        i1 += byte1;
        break MISSING_BLOCK_LABEL_161;
_L2:
        l3 = l2;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public DetectorResult detect()
        throws NotFoundException
    {
        ResultPoint aresultpoint[] = rectangleDetector.detect();
        ResultPoint resultpoint = aresultpoint[0];
        ResultPoint resultpoint1 = aresultpoint[1];
        ResultPoint resultpoint2 = aresultpoint[2];
        ResultPoint resultpoint3 = aresultpoint[3];
        Vector vector = new Vector(4);
        vector.addElement(transitionsBetween(resultpoint, resultpoint1));
        vector.addElement(transitionsBetween(resultpoint, resultpoint2));
        vector.addElement(transitionsBetween(resultpoint1, resultpoint3));
        vector.addElement(transitionsBetween(resultpoint2, resultpoint3));
        Collections.insertionSort(vector, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions resultpointsandtransitions = (ResultPointsAndTransitions)vector.elementAt(0);
        ResultPointsAndTransitions resultpointsandtransitions1 = (ResultPointsAndTransitions)vector.elementAt(1);
        Hashtable hashtable = new Hashtable();
        increment(hashtable, resultpointsandtransitions.getFrom());
        increment(hashtable, resultpointsandtransitions.getTo());
        increment(hashtable, resultpointsandtransitions1.getFrom());
        increment(hashtable, resultpointsandtransitions1.getTo());
        ResultPoint resultpoint4 = null;
        Enumeration enumeration = hashtable.keys();
        ResultPoint resultpoint5 = null;
        ResultPoint resultpoint6 = null;
        while(enumeration.hasMoreElements()) 
        {
            ResultPoint resultpoint12 = (ResultPoint)enumeration.nextElement();
            ResultPoint resultpoint13;
            ResultPoint resultpoint14;
            if(((Integer)hashtable.get(resultpoint12)).intValue() == 2)
            {
                resultpoint13 = resultpoint12;
                resultpoint12 = resultpoint4;
                resultpoint14 = resultpoint6;
            } else
            if(resultpoint6 == null)
            {
                resultpoint13 = resultpoint5;
                ResultPoint resultpoint15 = resultpoint4;
                resultpoint14 = resultpoint12;
                resultpoint12 = resultpoint15;
            } else
            {
                resultpoint13 = resultpoint5;
                resultpoint14 = resultpoint6;
            }
            resultpoint5 = resultpoint13;
            resultpoint6 = resultpoint14;
            resultpoint4 = resultpoint12;
        }
        if(resultpoint6 == null || resultpoint5 == null || resultpoint4 == null)
            throw NotFoundException.getNotFoundInstance();
        ResultPoint aresultpoint1[] = new ResultPoint[3];
        aresultpoint1[0] = resultpoint6;
        aresultpoint1[1] = resultpoint5;
        aresultpoint1[2] = resultpoint4;
        ResultPoint.orderBestPatterns(aresultpoint1);
        ResultPoint resultpoint7 = aresultpoint1[0];
        ResultPoint resultpoint8 = aresultpoint1[1];
        ResultPoint resultpoint9 = aresultpoint1[2];
        int i;
        ResultPoint resultpoint10;
        ResultPoint resultpoint11;
        int j;
        int k;
        BitMatrix bitmatrix;
        ResultPoint aresultpoint2[];
        if(hashtable.containsKey(resultpoint))
            if(!hashtable.containsKey(resultpoint1))
                resultpoint = resultpoint1;
            else
            if(!hashtable.containsKey(resultpoint2))
                resultpoint = resultpoint2;
            else
                resultpoint = resultpoint3;
        i = Math.min(transitionsBetween(resultpoint9, resultpoint).getTransitions(), transitionsBetween(resultpoint7, resultpoint).getTransitions());
        if((i & 1) == 1)
            i++;
        resultpoint10 = correctTopRight(resultpoint8, resultpoint7, resultpoint9, resultpoint, i + 2);
        if(resultpoint10 == null)
            resultpoint11 = resultpoint;
        else
            resultpoint11 = resultpoint10;
        j = 1 + Math.max(transitionsBetween(resultpoint9, resultpoint11).getTransitions(), transitionsBetween(resultpoint7, resultpoint11).getTransitions());
        if((j & 1) == 1)
            k = j + 1;
        else
            k = j;
        bitmatrix = sampleGrid(image, resultpoint9, resultpoint8, resultpoint7, resultpoint11, k);
        aresultpoint2 = new ResultPoint[4];
        aresultpoint2[0] = resultpoint9;
        aresultpoint2[1] = resultpoint8;
        aresultpoint2[2] = resultpoint7;
        aresultpoint2[3] = resultpoint11;
        return new DetectorResult(bitmatrix, aresultpoint2);
    }

    private static final Integer INTEGERS[];
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    static 
    {
        Integer ainteger[] = new Integer[5];
        ainteger[0] = new Integer(0);
        ainteger[1] = new Integer(1);
        ainteger[2] = new Integer(2);
        ainteger[3] = new Integer(3);
        ainteger[4] = new Integer(4);
        INTEGERS = ainteger;
    }
}
