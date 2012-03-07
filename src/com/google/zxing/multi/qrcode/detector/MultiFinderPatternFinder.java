// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.detector.*;
import java.util.Hashtable;
import java.util.Vector;

final class MultiFinderPatternFinder extends FinderPatternFinder
{
    private static class ModuleSizeComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            float f = ((FinderPattern)obj1).getEstimatedModuleSize() - ((FinderPattern)obj).getEstimatedModuleSize();
            byte byte0;
            if((double)f < 0.0D)
                byte0 = -1;
            else
            if((double)f > 0.0D)
                byte0 = 1;
            else
                byte0 = 0;
            return byte0;
        }

        private ModuleSizeComparator()
        {
        }

    }


    MultiFinderPatternFinder(BitMatrix bitmatrix)
    {
        super(bitmatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        super(bitmatrix, resultpointcallback);
    }

    private FinderPattern[][] selectBestPatterns()
        throws NotFoundException
    {
        Vector vector;
        int i;
        vector = getPossibleCenters();
        i = vector.size();
        if(i < 3)
            throw NotFoundException.getNotFoundInstance();
        if(i != 3) goto _L2; else goto _L1
_L1:
        FinderPattern afinderpattern[][];
        afinderpattern = new FinderPattern[1][];
        FinderPattern afinderpattern2[] = new FinderPattern[3];
        afinderpattern2[0] = (FinderPattern)vector.elementAt(0);
        afinderpattern2[1] = (FinderPattern)vector.elementAt(1);
        afinderpattern2[2] = (FinderPattern)vector.elementAt(2);
        afinderpattern[0] = afinderpattern2;
_L17:
        return afinderpattern;
_L2:
        Vector vector1;
        int j;
        Collections.insertionSort(vector, new ModuleSizeComparator());
        vector1 = new Vector();
        j = 0;
_L4:
        FinderPattern finderpattern;
        if(j >= i - 2)
            break MISSING_BLOCK_LABEL_501;
        finderpattern = (FinderPattern)vector.elementAt(j);
        if(finderpattern != null)
            break; /* Loop/switch isn't completed */
_L6:
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        int l = j + 1;
_L9:
        if(l >= i - 1) goto _L6; else goto _L5
_L5:
        FinderPattern finderpattern1 = (FinderPattern)vector.elementAt(l);
        if(finderpattern1 != null) goto _L8; else goto _L7
_L7:
        l++;
          goto _L9
_L8:
        float f = (finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) / Math.min(finderpattern.getEstimatedModuleSize(), finderpattern1.getEstimatedModuleSize());
        if(Math.abs(finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) > 0.5F && f >= 0.05F) goto _L6; else goto _L10
_L10:
        int i1 = l + 1;
_L14:
        if(i1 >= i) goto _L7; else goto _L11
_L11:
        FinderPattern finderpattern2 = (FinderPattern)vector.elementAt(i1);
        if(finderpattern2 != null) goto _L13; else goto _L12
_L12:
        i1++;
          goto _L14
_L13:
        float f1 = (finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) / Math.min(finderpattern1.getEstimatedModuleSize(), finderpattern2.getEstimatedModuleSize());
        if(Math.abs(finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) > 0.5F && f1 >= 0.05F) goto _L7; else goto _L15
_L15:
        FinderPattern afinderpattern1[] = new FinderPattern[3];
        afinderpattern1[0] = finderpattern;
        afinderpattern1[1] = finderpattern1;
        afinderpattern1[2] = finderpattern2;
        ResultPoint.orderBestPatterns(afinderpattern1);
        FinderPatternInfo finderpatterninfo = new FinderPatternInfo(afinderpattern1);
        float f2 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getBottomLeft());
        float f3 = ResultPoint.distance(finderpatterninfo.getTopRight(), finderpatterninfo.getBottomLeft());
        float f4 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getTopRight());
        float f5 = (f2 + f4) / finderpattern.getEstimatedModuleSize() / 2.0F;
        if(f5 <= 180F && f5 >= 9F && Math.abs((f2 - f4) / Math.min(f2, f4)) < 0.1F)
        {
            float f6 = (float)Math.sqrt(f2 * f2 + f4 * f4);
            if(Math.abs((f3 - f6) / Math.min(f3, f6)) < 0.1F)
                vector1.addElement(afinderpattern1);
        }
          goto _L12
        if(!vector1.isEmpty())
        {
            afinderpattern = new FinderPattern[vector1.size()][];
            int k = 0;
            while(k < vector1.size()) 
            {
                afinderpattern[k] = (FinderPattern[])(FinderPattern[])vector1.elementAt(k);
                k++;
            }
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if(true) goto _L17; else goto _L16
_L16:
    }

    public FinderPatternInfo[] findMulti(Hashtable hashtable)
        throws NotFoundException
    {
        boolean flag;
        BitMatrix bitmatrix;
        int i;
        int j;
        int k;
        int l;
        int ai[];
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        bitmatrix = getImage();
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth();
        k = (int)(3F * ((float)i / 228F));
        FinderPattern afinderpattern[][];
        Vector vector;
        FinderPatternInfo afinderpatterninfo[];
        if(k < 3 || flag)
            l = 3;
        else
            l = k;
        ai = new int[5];
        for(int i1 = l - 1; i1 < i; i1 += l)
        {
            ai[0] = 0;
            ai[1] = 0;
            ai[2] = 0;
            ai[3] = 0;
            ai[4] = 0;
            int l1 = 0;
            int i2 = 0;
            while(l1 < j) 
            {
                if(bitmatrix.get(l1, i1))
                {
                    if((i2 & 1) == 1)
                        i2++;
                    ai[i2] = 1 + ai[i2];
                } else
                if((i2 & 1) == 0)
                {
                    if(i2 == 4)
                    {
                        if(foundPatternCross(ai))
                        {
                            if(!handlePossibleCenter(ai, i1, l1))
                            {
                                while(++l1 < j && !bitmatrix.get(l1, i1)) ;
                                l1--;
                            }
                            ai[0] = 0;
                            ai[1] = 0;
                            ai[2] = 0;
                            ai[3] = 0;
                            ai[4] = 0;
                            i2 = 0;
                        } else
                        {
                            ai[0] = ai[2];
                            ai[1] = ai[3];
                            ai[2] = ai[4];
                            ai[3] = 1;
                            ai[4] = 0;
                            i2 = 3;
                        }
                    } else
                    {
                        i2++;
                        ai[i2] = 1 + ai[i2];
                    }
                } else
                {
                    ai[i2] = 1 + ai[i2];
                }
                l1++;
            }
            if(foundPatternCross(ai))
                handlePossibleCenter(ai, i1, j);
        }

        afinderpattern = selectBestPatterns();
        vector = new Vector();
        for(int j1 = 0; j1 < afinderpattern.length; j1++)
        {
            FinderPattern afinderpattern1[] = afinderpattern[j1];
            ResultPoint.orderBestPatterns(afinderpattern1);
            vector.addElement(new FinderPatternInfo(afinderpattern1));
        }

        if(vector.isEmpty())
        {
            afinderpatterninfo = EMPTY_RESULT_ARRAY;
        } else
        {
            afinderpatterninfo = new FinderPatternInfo[vector.size()];
            int k1 = 0;
            while(k1 < vector.size()) 
            {
                afinderpatterninfo[k1] = (FinderPatternInfo)vector.elementAt(k1);
                k1++;
            }
        }
        return afinderpatterninfo;
    }

    private static final float DIFF_MODSIZE_CUTOFF = 0.5F;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
    private static final FinderPatternInfo EMPTY_RESULT_ARRAY[] = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180F;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9F;

}
