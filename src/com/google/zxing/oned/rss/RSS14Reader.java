// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.oned.rss:
//            AbstractRSSReader, Pair, FinderPattern, RSSUtils, 
//            DataCharacter

public final class RSS14Reader extends AbstractRSSReader
{

    public RSS14Reader()
    {
    }

    private static void addOrTally(Vector vector, Pair pair)
    {
        if(pair != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        Enumeration enumeration = vector.elements();
        Pair pair1;
        do
        {
            if(!enumeration.hasMoreElements())
                break; /* Loop/switch isn't completed */
            pair1 = (Pair)enumeration.nextElement();
        } while(pair1.getValue() != pair.getValue());
        pair1.incrementCount();
        flag = true;
_L4:
        if(!flag)
            vector.addElement(pair);
        if(true) goto _L1; else goto _L3
_L3:
        flag = false;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void adjustOddEvenCounts(boolean flag, int i)
        throws NotFoundException
    {
        int j;
        int k;
        int l;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        j = count(oddCounts);
        k = count(evenCounts);
        l = (j + k) - i;
        int i1 = j & 1;
        int j1;
        if(flag)
            j1 = 1;
        else
            j1 = 0;
        if(i1 == j1)
            flag1 = true;
        else
            flag1 = false;
        if((k & 1) == 1)
            flag2 = true;
        else
            flag2 = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(j > 12)
        {
            flag3 = true;
            flag4 = false;
        } else
        if(j < 4)
        {
            flag3 = false;
            flag4 = true;
        } else
        {
            flag3 = false;
            flag4 = false;
        }
        if(k <= 12) goto _L4; else goto _L3
_L3:
        flag5 = flag3;
        flag6 = true;
        flag7 = flag4;
        flag8 = false;
_L7:
        if(l == 1)
        {
            if(flag1)
            {
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
                boolean flag9 = flag6;
                boolean flag10 = flag8;
                boolean flag11 = true;
                boolean flag12 = flag7;
                do
                {
                    if(flag12)
                    {
                        if(flag11)
                            throw NotFoundException.getNotFoundInstance();
                        increment(oddCounts, oddRoundingErrors);
                    }
                    if(flag11)
                        decrement(oddCounts, oddRoundingErrors);
                    if(flag10)
                    {
                        if(flag9)
                            throw NotFoundException.getNotFoundInstance();
                        increment(evenCounts, oddRoundingErrors);
                    }
                    if(flag9)
                        decrement(evenCounts, evenRoundingErrors);
                    return;
                } while(true);
            } else
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag9 = true;
                flag10 = flag8;
                flag11 = flag5;
                flag12 = flag7;
                continue;
            }
        } else
        {
            if(l == -1)
            {
                if(flag1)
                {
                    if(flag2)
                        throw NotFoundException.getNotFoundInstance();
                    flag9 = flag6;
                    flag10 = flag8;
                    flag11 = flag5;
                    flag12 = true;
                } else
                {
                    if(!flag2)
                        throw NotFoundException.getNotFoundInstance();
                    flag9 = flag6;
                    flag10 = true;
                    flag11 = flag5;
                    flag12 = flag7;
                }
                continue;
            }
            if(l == 0)
            {
                if(flag1)
                {
                    if(!flag2)
                        throw NotFoundException.getNotFoundInstance();
                    if(j < k)
                    {
                        flag9 = true;
                        flag10 = flag8;
                        flag11 = flag5;
                        flag12 = true;
                    } else
                    {
                        flag9 = flag6;
                        flag10 = true;
                        flag11 = true;
                        flag12 = flag7;
                    }
                    continue;
                }
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
            } else
            {
                throw NotFoundException.getNotFoundInstance();
            }
            flag9 = flag6;
            flag10 = flag8;
            flag11 = flag5;
            flag12 = flag7;
            continue;
        }
_L4:
        if(k < 4)
        {
            flag5 = flag3;
            flag6 = false;
            flag7 = flag4;
            flag8 = true;
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
_L2:
        if(j > 11)
        {
            flag3 = true;
            flag4 = false;
        } else
        if(j < 5)
        {
            flag3 = false;
            flag4 = true;
        } else
        {
            flag3 = false;
            flag4 = false;
        }
        if(k > 10)
        {
            flag5 = flag3;
            flag6 = true;
            flag7 = flag4;
            flag8 = false;
            continue; /* Loop/switch isn't completed */
        }
        if(k < 4)
        {
            flag5 = flag3;
            flag6 = false;
            flag7 = flag4;
            flag8 = true;
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag5 = flag3;
        flag6 = false;
        flag7 = flag4;
        flag8 = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static boolean checkChecksum(Pair pair, Pair pair1)
    {
        int i = pair.getFinderPattern().getValue();
        int j = pair1.getFinderPattern().getValue();
        if((i != 0 || j != 8) && i == 8)
            if(j != 0);
        int k = (pair.getChecksumPortion() + 16 * pair1.getChecksumPortion()) % 79;
        int l = 9 * pair.getFinderPattern().getValue() + pair1.getFinderPattern().getValue();
        if(l > 72)
            l--;
        if(l > 8)
            l--;
        boolean flag;
        if(k == l)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static Result constructResult(Pair pair, Pair pair1)
    {
        String s = String.valueOf(0x453af5L * (long)pair.getValue() + (long)pair1.getValue());
        StringBuffer stringbuffer = new StringBuffer(14);
        for(int i = 13 - s.length(); i > 0; i--)
            stringbuffer.append('0');

        stringbuffer.append(s);
        int j = 0;
        int k = 0;
        for(; j < 13; j++)
        {
            int i1 = stringbuffer.charAt(j) - 48;
            if((j & 1) == 0)
                i1 *= 3;
            k += i1;
        }

        int l = 10 - k % 10;
        if(l == 10)
            l = 0;
        stringbuffer.append(l);
        ResultPoint aresultpoint[] = pair.getFinderPattern().getResultPoints();
        ResultPoint aresultpoint1[] = pair1.getFinderPattern().getResultPoints();
        String s1 = String.valueOf(stringbuffer.toString());
        ResultPoint aresultpoint2[] = new ResultPoint[4];
        aresultpoint2[0] = aresultpoint[0];
        aresultpoint2[1] = aresultpoint[1];
        aresultpoint2[2] = aresultpoint1[0];
        aresultpoint2[3] = aresultpoint1[1];
        return new Result(s1, null, aresultpoint2, BarcodeFormat.RSS14);
    }

    private DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag)
        throws NotFoundException
    {
        int ai[] = dataCharacterCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        ai[5] = 0;
        ai[6] = 0;
        ai[7] = 0;
        int i1;
        float f;
        int ai1[];
        int ai2[];
        float af[];
        float af1[];
        int j1;
        if(flag)
        {
            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
        } else
        {
            recordPattern(bitarray, 1 + finderpattern.getStartEnd()[1], ai);
            int i = ai.length - 1;
            int j = 0;
            int k = i;
            while(j < k) 
            {
                int l = ai[j];
                ai[j] = ai[k];
                ai[k] = l;
                j++;
                k--;
            }
        }
        if(flag)
            i1 = 16;
        else
            i1 = 15;
        f = (float)count(ai) / (float)i1;
        ai1 = oddCounts;
        ai2 = evenCounts;
        af = oddRoundingErrors;
        af1 = evenRoundingErrors;
        j1 = 0;
        do
        {
            if(j1 >= ai.length)
                break;
            float f1 = (float)ai[j1] / f;
            int l6 = (int)(0.5F + f1);
            int i7;
            if(l6 < 1)
                l6 = 1;
            else
            if(l6 > 8)
                l6 = 8;
            i7 = j1 >> 1;
            if((j1 & 1) == 0)
            {
                ai1[i7] = l6;
                af[i7] = f1 - (float)l6;
            } else
            {
                ai2[i7] = l6;
                af1[i7] = f1 - (float)l6;
            }
            j1++;
        } while(true);
        adjustOddEvenCounts(flag, i1);
        int k1 = 0;
        int l1 = ai1.length - 1;
        int i2 = 0;
        for(int j2 = l1; j2 >= 0; j2--)
        {
            k1 = k1 * 9 + ai1[j2];
            i2 += ai1[j2];
        }

        int k2 = 0;
        int l2 = ai2.length - 1;
        int i3 = 0;
        for(int j3 = l2; j3 >= 0; j3--)
        {
            i3 = i3 * 9 + ai2[j3];
            k2 += ai2[j3];
        }

        int k3 = k1 + i3 * 3;
        DataCharacter datacharacter;
        if(flag)
        {
            if((i2 & 1) != 0 || i2 > 12 || i2 < 4)
                throw NotFoundException.getNotFoundInstance();
            int j5 = (12 - i2) / 2;
            int k5 = OUTSIDE_ODD_WIDEST[j5];
            int l5 = 9 - k5;
            int i6 = RSSUtils.getRSSvalue(ai1, k5, false);
            int j6 = RSSUtils.getRSSvalue(ai2, l5, true);
            int k6 = OUTSIDE_EVEN_TOTAL_SUBSET[j5];
            datacharacter = new DataCharacter(OUTSIDE_GSUM[j5] + (j6 + i6 * k6), k3);
        } else
        {
            if((k2 & 1) != 0 || k2 > 10 || k2 < 4)
                throw NotFoundException.getNotFoundInstance();
            int l3 = (10 - k2) / 2;
            int i4 = INSIDE_ODD_WIDEST[l3];
            int j4 = 9 - i4;
            int k4 = RSSUtils.getRSSvalue(ai1, i4, true);
            int l4 = RSSUtils.getRSSvalue(ai2, j4, false);
            int i5 = INSIDE_ODD_TOTAL_SUBSET[l3];
            datacharacter = new DataCharacter(INSIDE_GSUM[l3] + (k4 + l4 * i5), k3);
        }
        return datacharacter;
    }

    private Pair decodePair(BitArray bitarray, boolean flag, int i, Hashtable hashtable)
    {
        Pair pair;
        try
        {
            int ai[] = findFinderPattern(bitarray, 0, flag);
            FinderPattern finderpattern = parseFoundFinderPattern(bitarray, i, flag, ai);
            ResultPointCallback resultpointcallback;
            DataCharacter datacharacter;
            DataCharacter datacharacter1;
            if(hashtable == null)
                resultpointcallback = null;
            else
                resultpointcallback = (ResultPointCallback)hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if(resultpointcallback != null)
            {
                float f = (float)(ai[0] + ai[1]) / 2.0F;
                if(flag)
                    f = (float)(bitarray.getSize() - 1) - f;
                resultpointcallback.foundPossibleResultPoint(new ResultPoint(f, i));
            }
            datacharacter = decodeDataCharacter(bitarray, finderpattern, true);
            datacharacter1 = decodeDataCharacter(bitarray, finderpattern, false);
            pair = new Pair(1597 * datacharacter.getValue() + datacharacter1.getValue(), datacharacter.getChecksumPortion() + 4 * datacharacter1.getChecksumPortion(), finderpattern);
        }
        catch(NotFoundException notfoundexception)
        {
            pair = null;
        }
        return pair;
    }

    private int[] findFinderPattern(BitArray bitarray, int i, boolean flag)
        throws NotFoundException
    {
        int ai[] = decodeFinderCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        int j = bitarray.getSize();
        boolean flag1 = false;
        int k = i;
        do
        {
label0:
            {
label1:
                {
                    int l;
                    boolean flag2;
                    int i1;
                    if(k < j)
                    {
                        if(!bitarray.get(k))
                            flag1 = true;
                        else
                            flag1 = false;
                        if(flag != flag1)
                            break label1;
                    }
                    l = 0;
                    flag2 = flag1;
                    i1 = k;
                    while(i1 < j) 
                    {
                        if(flag2 ^ bitarray.get(i1))
                        {
                            ai[l] = 1 + ai[l];
                        } else
                        {
                            if(l == 3)
                            {
                                if(isFinderPattern(ai))
                                {
                                    int ai1[] = new int[2];
                                    ai1[0] = k;
                                    ai1[1] = i1;
                                    return ai1;
                                }
                                k += ai[0] + ai[1];
                                ai[0] = ai[2];
                                ai[1] = ai[3];
                                ai[2] = 0;
                                ai[3] = 0;
                                l--;
                            } else
                            {
                                l++;
                            }
                            ai[l] = 1;
                            if(!flag2)
                                flag2 = true;
                            else
                                flag2 = false;
                        }
                        i1++;
                    }
                    break label0;
                }
                k++;
                continue;
            }
            throw NotFoundException.getNotFoundInstance();
        } while(true);
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag, int ai[])
        throws NotFoundException
    {
        boolean flag1 = bitarray.get(ai[0]);
        int j;
        for(j = ai[0] - 1; j >= 0 && flag1 ^ bitarray.get(j); j--);
        int k = j + 1;
        int l = ai[0] - k;
        int ai1[] = decodeFinderCounters;
        for(int i1 = ai1.length - 1; i1 > 0; i1--)
            ai1[i1] = ai1[i1 - 1];

        ai1[0] = l;
        int j1 = parseFinderValue(ai1, FINDER_PATTERNS);
        int k1 = ai[1];
        int l1;
        int i2;
        int ai2[];
        if(flag)
        {
            int j2 = bitarray.getSize() - 1 - k;
            l1 = bitarray.getSize() - 1 - k1;
            i2 = j2;
        } else
        {
            l1 = k1;
            i2 = k;
        }
        ai2 = new int[2];
        ai2[0] = k;
        ai2[1] = ai[1];
        return new FinderPattern(j1, ai2, i2, l1, i);
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        Pair pair = decodePair(bitarray, false, i, hashtable);
        addOrTally(possibleLeftPairs, pair);
        bitarray.reverse();
        Pair pair1 = decodePair(bitarray, true, i, hashtable);
        addOrTally(possibleRightPairs, pair1);
        bitarray.reverse();
        int j = possibleLeftPairs.size();
        int k = possibleRightPairs.size();
label0:
        for(int l = 0; l < j; l++)
        {
            Pair pair2 = (Pair)possibleLeftPairs.elementAt(l);
            if(pair2.getCount() <= 1)
                continue;
            int i1 = 0;
            do
            {
                if(i1 >= k)
                    continue label0;
                Pair pair3 = (Pair)possibleRightPairs.elementAt(i1);
                if(pair3.getCount() > 1 && checkChecksum(pair2, pair3))
                    return constructResult(pair2, pair3);
                i1++;
            } while(true);
        }

        throw NotFoundException.getNotFoundInstance();
    }

    public void reset()
    {
        possibleLeftPairs.setSize(0);
        possibleRightPairs.setSize(0);
    }

    private static final int FINDER_PATTERNS[][];
    private static final int INSIDE_GSUM[];
    private static final int INSIDE_ODD_TOTAL_SUBSET[];
    private static final int INSIDE_ODD_WIDEST[];
    private static final int OUTSIDE_EVEN_TOTAL_SUBSET[];
    private static final int OUTSIDE_GSUM[];
    private static final int OUTSIDE_ODD_WIDEST[];
    private final Vector possibleLeftPairs = new Vector();
    private final Vector possibleRightPairs = new Vector();

    static 
    {
        int ai[] = new int[5];
        ai[0] = 1;
        ai[1] = 10;
        ai[2] = 34;
        ai[3] = 70;
        ai[4] = 126;
        OUTSIDE_EVEN_TOTAL_SUBSET = ai;
        int ai1[] = new int[4];
        ai1[0] = 4;
        ai1[1] = 20;
        ai1[2] = 48;
        ai1[3] = 81;
        INSIDE_ODD_TOTAL_SUBSET = ai1;
        int ai2[] = new int[5];
        ai2[0] = 0;
        ai2[1] = 161;
        ai2[2] = 961;
        ai2[3] = 2015;
        ai2[4] = 2715;
        OUTSIDE_GSUM = ai2;
        int ai3[] = new int[4];
        ai3[0] = 0;
        ai3[1] = 336;
        ai3[2] = 1036;
        ai3[3] = 1516;
        INSIDE_GSUM = ai3;
        int ai4[] = new int[5];
        ai4[0] = 8;
        ai4[1] = 6;
        ai4[2] = 4;
        ai4[3] = 3;
        ai4[4] = 1;
        OUTSIDE_ODD_WIDEST = ai4;
        int ai5[] = new int[4];
        ai5[0] = 2;
        ai5[1] = 4;
        ai5[2] = 6;
        ai5[3] = 8;
        INSIDE_ODD_WIDEST = ai5;
        int ai6[][] = new int[9][];
        int ai7[] = new int[4];
        ai7[0] = 3;
        ai7[1] = 8;
        ai7[2] = 2;
        ai7[3] = 1;
        ai6[0] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 3;
        ai8[1] = 5;
        ai8[2] = 5;
        ai8[3] = 1;
        ai6[1] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 3;
        ai9[1] = 3;
        ai9[2] = 7;
        ai9[3] = 1;
        ai6[2] = ai9;
        int ai10[] = new int[4];
        ai10[0] = 3;
        ai10[1] = 1;
        ai10[2] = 9;
        ai10[3] = 1;
        ai6[3] = ai10;
        int ai11[] = new int[4];
        ai11[0] = 2;
        ai11[1] = 7;
        ai11[2] = 4;
        ai11[3] = 1;
        ai6[4] = ai11;
        int ai12[] = new int[4];
        ai12[0] = 2;
        ai12[1] = 5;
        ai12[2] = 6;
        ai12[3] = 1;
        ai6[5] = ai12;
        int ai13[] = new int[4];
        ai13[0] = 2;
        ai13[1] = 3;
        ai13[2] = 8;
        ai13[3] = 1;
        ai6[6] = ai13;
        int ai14[] = new int[4];
        ai14[0] = 1;
        ai14[1] = 5;
        ai14[2] = 7;
        ai14[3] = 1;
        ai6[7] = ai14;
        int ai15[] = new int[4];
        ai15[0] = 1;
        ai15[1] = 3;
        ai15[2] = 9;
        ai15[3] = 1;
        ai6[8] = ai15;
        FINDER_PATTERNS = ai6;
    }
}
