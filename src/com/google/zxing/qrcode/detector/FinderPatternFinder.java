// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern, FinderPatternInfo

public class FinderPatternFinder
{
    private static class CenterComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            int i;
            if(((FinderPattern)obj1).getCount() != ((FinderPattern)obj).getCount())
            {
                i = ((FinderPattern)obj1).getCount() - ((FinderPattern)obj).getCount();
            } else
            {
                float f = Math.abs(((FinderPattern)obj1).getEstimatedModuleSize() - average);
                float f1 = Math.abs(((FinderPattern)obj).getEstimatedModuleSize() - average);
                if(f < f1)
                    i = 1;
                else
                if(f == f1)
                    i = 0;
                else
                    i = -1;
            }
            return i;
        }

        private final float average;

        public CenterComparator(float f)
        {
            average = f;
        }
    }

    private static class FurthestFromAverageComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            float f = Math.abs(((FinderPattern)obj1).getEstimatedModuleSize() - average);
            float f1 = Math.abs(((FinderPattern)obj).getEstimatedModuleSize() - average);
            byte byte0;
            if(f < f1)
                byte0 = -1;
            else
            if(f == f1)
                byte0 = 0;
            else
                byte0 = 1;
            return byte0;
        }

        private final float average;

        public FurthestFromAverageComparator(float f)
        {
            average = f;
        }
    }


    public FinderPatternFinder(BitMatrix bitmatrix)
    {
        this(bitmatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        image = bitmatrix;
        possibleCenters = new Vector();
        crossCheckStateCount = new int[5];
        resultPointCallback = resultpointcallback;
    }

    private static float centerFromEnd(int ai[], int i)
    {
        return (float)(i - ai[4] - ai[3]) - (float)ai[2] / 2.0F;
    }

    private float crossCheckHorizontal(int i, int j, int k, int l)
    {
        BitMatrix bitmatrix = image;
        int i1 = bitmatrix.getWidth();
        int ai[] = getCrossCheckStateCount();
        int j1;
        for(j1 = i; j1 >= 0 && bitmatrix.get(j1, j); j1--)
            ai[2] = 1 + ai[2];

        float f;
        if(j1 < 0)
        {
            f = (0.0F / 0.0F);
        } else
        {
            for(; j1 >= 0 && !bitmatrix.get(j1, j) && ai[1] <= k; j1--)
                ai[1] = 1 + ai[1];

            if(j1 < 0 || ai[1] > k)
            {
                f = (0.0F / 0.0F);
            } else
            {
                for(; j1 >= 0 && bitmatrix.get(j1, j) && ai[0] <= k; j1--)
                    ai[0] = 1 + ai[0];

                if(ai[0] > k)
                {
                    f = (0.0F / 0.0F);
                } else
                {
                    int k1;
                    for(k1 = i + 1; k1 < i1 && bitmatrix.get(k1, j); k1++)
                        ai[2] = 1 + ai[2];

                    if(k1 == i1)
                    {
                        f = (0.0F / 0.0F);
                    } else
                    {
                        for(; k1 < i1 && !bitmatrix.get(k1, j) && ai[3] < k; k1++)
                            ai[3] = 1 + ai[3];

                        if(k1 == i1 || ai[3] >= k)
                        {
                            f = (0.0F / 0.0F);
                        } else
                        {
                            for(; k1 < i1 && bitmatrix.get(k1, j) && ai[4] < k; k1++)
                                ai[4] = 1 + ai[4];

                            if(ai[4] >= k)
                                f = (0.0F / 0.0F);
                            else
                            if(5 * Math.abs((ai[0] + ai[1] + ai[2] + ai[3] + ai[4]) - l) >= l)
                                f = (0.0F / 0.0F);
                            else
                            if(foundPatternCross(ai))
                                f = centerFromEnd(ai, k1);
                            else
                                f = (0.0F / 0.0F);
                        }
                    }
                }
            }
        }
        return f;
    }

    private float crossCheckVertical(int i, int j, int k, int l)
    {
        BitMatrix bitmatrix = image;
        int i1 = bitmatrix.getHeight();
        int ai[] = getCrossCheckStateCount();
        int j1;
        for(j1 = i; j1 >= 0 && bitmatrix.get(j, j1); j1--)
            ai[2] = 1 + ai[2];

        float f;
        if(j1 < 0)
        {
            f = (0.0F / 0.0F);
        } else
        {
            for(; j1 >= 0 && !bitmatrix.get(j, j1) && ai[1] <= k; j1--)
                ai[1] = 1 + ai[1];

            if(j1 < 0 || ai[1] > k)
            {
                f = (0.0F / 0.0F);
            } else
            {
                for(; j1 >= 0 && bitmatrix.get(j, j1) && ai[0] <= k; j1--)
                    ai[0] = 1 + ai[0];

                if(ai[0] > k)
                {
                    f = (0.0F / 0.0F);
                } else
                {
                    int k1;
                    for(k1 = i + 1; k1 < i1 && bitmatrix.get(j, k1); k1++)
                        ai[2] = 1 + ai[2];

                    if(k1 == i1)
                    {
                        f = (0.0F / 0.0F);
                    } else
                    {
                        for(; k1 < i1 && !bitmatrix.get(j, k1) && ai[3] < k; k1++)
                            ai[3] = 1 + ai[3];

                        if(k1 == i1 || ai[3] >= k)
                        {
                            f = (0.0F / 0.0F);
                        } else
                        {
                            for(; k1 < i1 && bitmatrix.get(j, k1) && ai[4] < k; k1++)
                                ai[4] = 1 + ai[4];

                            if(ai[4] >= k)
                                f = (0.0F / 0.0F);
                            else
                            if(5 * Math.abs((ai[0] + ai[1] + ai[2] + ai[3] + ai[4]) - l) >= l * 2)
                                f = (0.0F / 0.0F);
                            else
                            if(foundPatternCross(ai))
                                f = centerFromEnd(ai, k1);
                            else
                                f = (0.0F / 0.0F);
                        }
                    }
                }
            }
        }
        return f;
    }

    private int findRowSkip()
    {
        int i = possibleCenters.size();
        if(i > 1) goto _L2; else goto _L1
_L1:
        int k = 0;
_L8:
        return k;
_L2:
        int j;
        FinderPattern finderpattern;
        j = 0;
        finderpattern = null;
_L7:
        if(j >= i) goto _L4; else goto _L3
_L3:
        FinderPattern finderpattern1;
        finderpattern1 = (FinderPattern)possibleCenters.elementAt(j);
        if(finderpattern1.getCount() < 2)
            break MISSING_BLOCK_LABEL_110;
        if(finderpattern != null) goto _L6; else goto _L5
_L5:
        j++;
        finderpattern = finderpattern1;
          goto _L7
_L6:
        hasSkipped = true;
        k = (int)(Math.abs(finderpattern.getX() - finderpattern1.getX()) - Math.abs(finderpattern.getY() - finderpattern1.getY())) / 2;
          goto _L8
_L4:
        k = 0;
          goto _L8
        finderpattern1 = finderpattern;
          goto _L5
    }

    protected static boolean foundPatternCross(int ai[])
    {
        int i;
        int j;
        i = 0;
        j = 0;
_L3:
        int i1;
        if(i >= 5)
            break MISSING_BLOCK_LABEL_36;
        i1 = ai[i];
        if(i1 != 0) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        j += i1;
        i++;
          goto _L3
        if(j < 7)
        {
            flag = false;
        } else
        {
            int k = (j << 8) / 7;
            int l = k / 2;
            if(Math.abs(k - (ai[0] << 8)) < l && Math.abs(k - (ai[1] << 8)) < l && Math.abs(k * 3 - (ai[2] << 8)) < l * 3 && Math.abs(k - (ai[3] << 8)) < l && Math.abs(k - (ai[4] << 8)) < l)
                flag = true;
            else
                flag = false;
        }
          goto _L4
    }

    private int[] getCrossCheckStateCount()
    {
        crossCheckStateCount[0] = 0;
        crossCheckStateCount[1] = 0;
        crossCheckStateCount[2] = 0;
        crossCheckStateCount[3] = 0;
        crossCheckStateCount[4] = 0;
        return crossCheckStateCount;
    }

    private boolean haveMultiplyConfirmedCenters()
    {
        int i = possibleCenters.size();
        int j = 0;
        float f = 0.0F;
        int k = 0;
        while(j < i) 
        {
            FinderPattern finderpattern = (FinderPattern)possibleCenters.elementAt(j);
            float f1;
            int l;
            float f2;
            boolean flag;
            float f3;
            float f4;
            int i1;
            if(finderpattern.getCount() >= 2)
            {
                int j1 = k + 1;
                f4 = f + finderpattern.getEstimatedModuleSize();
                i1 = j1;
            } else
            {
                f4 = f;
                i1 = k;
            }
            j++;
            k = i1;
            f = f4;
        }
        if(k < 3)
        {
            flag = false;
        } else
        {
            f1 = f / (float)i;
            l = 0;
            for(f2 = 0.0F; l < i; f2 = f3)
            {
                f3 = f2 + Math.abs(((FinderPattern)possibleCenters.elementAt(l)).getEstimatedModuleSize() - f1);
                l++;
            }

            if(f2 <= 0.05F * f)
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    private FinderPattern[] selectBestPatterns()
        throws NotFoundException
    {
        int i = possibleCenters.size();
        if(i < 3)
            throw NotFoundException.getNotFoundInstance();
        if(i > 3)
        {
            int k = 0;
            float f3 = 0.0F;
            float f4 = 0.0F;
            while(k < i) 
            {
                float f8 = ((FinderPattern)possibleCenters.elementAt(k)).getEstimatedModuleSize();
                f4 += f8;
                float f9 = f3 + f8 * f8;
                k++;
                f3 = f9;
            }
            float f5 = f4 / (float)i;
            float f6 = (float)Math.sqrt(f3 / (float)i - f5 * f5);
            Collections.insertionSort(possibleCenters, new FurthestFromAverageComparator(f5));
            float f7 = Math.max(0.2F * f5, f6);
            int l = 0;
            while(l < possibleCenters.size() && possibleCenters.size() > 3) 
            {
                FinderPattern afinderpattern[];
                int j;
                float f;
                float f1;
                float f2;
                int i1;
                if(Math.abs(((FinderPattern)possibleCenters.elementAt(l)).getEstimatedModuleSize() - f5) > f7)
                {
                    possibleCenters.removeElementAt(l);
                    i1 = l - 1;
                } else
                {
                    i1 = l;
                }
                l = i1 + 1;
            }
        }
        if(possibleCenters.size() > 3)
        {
            j = 0;
            for(f = 0.0F; j < possibleCenters.size(); f = f2)
            {
                f2 = f + ((FinderPattern)possibleCenters.elementAt(j)).getEstimatedModuleSize();
                j++;
            }

            f1 = f / (float)possibleCenters.size();
            Collections.insertionSort(possibleCenters, new CenterComparator(f1));
            possibleCenters.setSize(3);
        }
        afinderpattern = new FinderPattern[3];
        afinderpattern[0] = (FinderPattern)possibleCenters.elementAt(0);
        afinderpattern[1] = (FinderPattern)possibleCenters.elementAt(1);
        afinderpattern[2] = (FinderPattern)possibleCenters.elementAt(2);
        return afinderpattern;
    }

    FinderPatternInfo find(Hashtable hashtable)
        throws NotFoundException
    {
        boolean flag;
        int i;
        int j;
        int k;
        int l;
        boolean flag1;
        int ai[];
        int i1;
        int j1;
        int k1;
        byte byte0;
        int l1;
        int i2;
        boolean flag2;
        int j2;
        boolean flag3;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        i = image.getHeight();
        j = image.getWidth();
        k = (i * 3) / 228;
        FinderPattern afinderpattern[];
        boolean flag4;
        int k2;
        int l2;
        int i3;
        boolean flag5;
        int j3;
        int k3;
        int l3;
        if(k < 3 || flag)
            l = 3;
        else
            l = k;
        flag1 = false;
        ai = new int[5];
        i1 = l - 1;
        j1 = l;
        k1 = i1;
        if(k1 < i && !flag1)
        {
            ai[0] = 0;
            ai[1] = 0;
            ai[2] = 0;
            ai[3] = 0;
            ai[4] = 0;
            byte0 = j1;
            l1 = k1;
            i2 = 0;
            flag2 = flag1;
            j2 = 0;
            flag3 = flag2;
            while(i2 < j) 
            {
                if(image.get(i2, l1))
                {
                    if((j2 & 1) == 1)
                        j2++;
                    ai[j2] = 1 + ai[j2];
                } else
                if((j2 & 1) == 0)
                {
                    if(j2 == 4)
                    {
                        if(foundPatternCross(ai))
                        {
                            if(handlePossibleCenter(ai, l1, i2))
                            {
                                if(hasSkipped)
                                {
                                    flag3 = haveMultiplyConfirmedCenters();
                                } else
                                {
                                    j3 = findRowSkip();
                                    if(j3 > ai[2])
                                    {
                                        k3 = l1 + (j3 - ai[2] - 2);
                                        l3 = j - 1;
                                        l1 = k3;
                                        i2 = l3;
                                    }
                                }
                                ai[0] = 0;
                                ai[1] = 0;
                                ai[2] = 0;
                                ai[3] = 0;
                                ai[4] = 0;
                                byte0 = 2;
                                j2 = 0;
                            } else
                            {
                                ai[0] = ai[2];
                                ai[1] = ai[3];
                                ai[2] = ai[4];
                                ai[3] = 1;
                                ai[4] = 0;
                                j2 = 3;
                            }
                        } else
                        {
                            ai[0] = ai[2];
                            ai[1] = ai[3];
                            ai[2] = ai[4];
                            ai[3] = 1;
                            ai[4] = 0;
                            j2 = 3;
                        }
                    } else
                    {
                        j2++;
                        ai[j2] = 1 + ai[j2];
                    }
                } else
                {
                    ai[j2] = 1 + ai[j2];
                }
                i2++;
            }
            if(foundPatternCross(ai) && handlePossibleCenter(ai, l1, j))
            {
                i3 = ai[0];
                if(hasSkipped)
                {
                    flag5 = haveMultiplyConfirmedCenters();
                    k2 = i3;
                    flag4 = flag5;
                } else
                {
                    k2 = i3;
                    flag4 = flag3;
                }
            } else
            {
                flag4 = flag3;
                k2 = byte0;
            }
            l2 = l1 + k2;
            j1 = k2;
            flag1 = flag4;
            k1 = l2;
            if(false)
                break MISSING_BLOCK_LABEL_500;
            else
                break MISSING_BLOCK_LABEL_77;
        }
        afinderpattern = selectBestPatterns();
        ResultPoint.orderBestPatterns(afinderpattern);
        return new FinderPatternInfo(afinderpattern);
    }

    protected BitMatrix getImage()
    {
        return image;
    }

    protected Vector getPossibleCenters()
    {
        return possibleCenters;
    }

    protected boolean handlePossibleCenter(int ai[], int i, int j)
    {
        int k;
        float f;
        float f1;
        k = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
        f = centerFromEnd(ai, j);
        f1 = crossCheckVertical(i, (int)f, ai[2], k);
        if(Float.isNaN(f1)) goto _L2; else goto _L1
_L1:
        float f2 = crossCheckHorizontal((int)f, (int)f1, ai[2], k);
        if(Float.isNaN(f2)) goto _L2; else goto _L3
_L3:
        float f3;
        int l;
        int i1;
        f3 = (float)k / 7F;
        l = possibleCenters.size();
        i1 = 0;
_L6:
        FinderPattern finderpattern1;
        if(i1 >= l)
            break MISSING_BLOCK_LABEL_204;
        finderpattern1 = (FinderPattern)possibleCenters.elementAt(i1);
        if(!finderpattern1.aboutEquals(f3, f1, f2)) goto _L5; else goto _L4
_L4:
        boolean flag1;
        finderpattern1.incrementCount();
        flag1 = true;
_L8:
        boolean flag;
        if(!flag1)
        {
            FinderPattern finderpattern = new FinderPattern(f2, f1, f3);
            possibleCenters.addElement(finderpattern);
            if(resultPointCallback != null)
                resultPointCallback.foundPossibleResultPoint(finderpattern);
        }
        flag = true;
_L7:
        return flag;
_L5:
        i1++;
          goto _L6
_L2:
        flag = false;
          goto _L7
        flag1 = false;
          goto _L8
    }

    private static final int CENTER_QUORUM = 2;
    private static final int INTEGER_MATH_SHIFT = 8;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int crossCheckStateCount[];
    private boolean hasSkipped;
    private final BitMatrix image;
    private final Vector possibleCenters;
    private final ResultPointCallback resultPointCallback;
}
