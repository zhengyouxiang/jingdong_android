// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.*;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            ExpandedPair, BitArrayBuilder

public final class RSSExpandedReader extends AbstractRSSReader
{

    public RSSExpandedReader()
    {
        currentSequence = new int[LONGEST_SEQUENCE_SIZE];
    }

    private void adjustOddEvenCounts(int i)
        throws NotFoundException
    {
        boolean flag = false;
        int j = count(oddCounts);
        int k = count(evenCounts);
        int l = (j + k) - i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        if((j & 1) == 1)
            flag1 = true;
        else
            flag1 = false;
        if((k & 1) == 0)
            flag2 = true;
        else
            flag2 = false;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        if(j > 13)
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
        if(k > 13)
        {
            flag5 = false;
            flag = true;
        } else
        if(k < 4)
            flag5 = true;
        else
            flag5 = false;
        if(l == 1)
        {
            if(flag1)
            {
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag6 = flag;
                flag7 = flag5;
                flag8 = true;
                flag9 = flag4;
            } else
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag6 = true;
                flag7 = flag5;
                flag8 = flag3;
                flag9 = flag4;
            }
            break MISSING_BLOCK_LABEL_143;
        }
        if(l == -1)
        {
            if(flag1)
            {
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag6 = flag;
                flag7 = flag5;
                flag8 = flag3;
                flag9 = true;
            } else
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag6 = flag;
                flag7 = true;
                flag8 = flag3;
                flag9 = flag4;
            }
            continue; /* Loop/switch isn't completed */
        }
        if(l == 0)
        {
            if(flag1)
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                if(j < k)
                {
                    flag6 = true;
                    flag7 = flag5;
                    flag8 = flag3;
                    flag9 = true;
                } else
                {
                    flag6 = flag;
                    flag7 = true;
                    flag8 = true;
                    flag9 = flag4;
                }
                continue; /* Loop/switch isn't completed */
            }
            if(flag2)
                throw NotFoundException.getNotFoundInstance();
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
        flag6 = flag;
        flag7 = flag5;
        flag8 = flag3;
        flag9 = flag4;
          goto _L1
_L3:
        if(flag9)
        {
            if(flag8)
                throw NotFoundException.getNotFoundInstance();
            increment(oddCounts, oddRoundingErrors);
        }
        if(flag8)
            decrement(oddCounts, oddRoundingErrors);
        if(flag7)
        {
            if(flag6)
                throw NotFoundException.getNotFoundInstance();
            increment(evenCounts, oddRoundingErrors);
        }
        if(flag6)
            decrement(evenCounts, evenRoundingErrors);
        return;
_L1:
        if(true) goto _L3; else goto _L2
_L2:
    }

    private boolean checkChecksum()
    {
        ExpandedPair expandedpair = (ExpandedPair)pairs.elementAt(0);
        DataCharacter datacharacter = expandedpair.getLeftChar();
        int i = expandedpair.getRightChar().getChecksumPortion();
        int j = 2;
        int k = i;
        int l = 1;
        while(l < pairs.size()) 
        {
            ExpandedPair expandedpair1 = (ExpandedPair)pairs.elementAt(l);
            int i1 = k + expandedpair1.getLeftChar().getChecksumPortion();
            int j1 = j + 1;
            boolean flag;
            int k1;
            int l1;
            if(expandedpair1.getRightChar() != null)
            {
                int i2 = i1 + expandedpair1.getRightChar().getChecksumPortion();
                int j2 = j1 + 1;
                l1 = i2;
                k1 = j2;
            } else
            {
                k1 = j1;
                l1 = i1;
            }
            l++;
            k = l1;
            j = k1;
        }
        if(k % 211 + 211 * (j - 4) == datacharacter.getValue())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean checkPairSequence(Vector vector, FinderPattern finderpattern)
        throws NotFoundException
    {
        int i;
        int k;
        i = 1 + vector.size();
        if(i > currentSequence.length)
            throw NotFoundException.getNotFoundInstance();
        for(int j = 0; j < vector.size(); j++)
            currentSequence[j] = ((ExpandedPair)vector.elementAt(j)).getFinderPattern().getValue();

        currentSequence[i - 1] = finderpattern.getValue();
        k = 0;
_L6:
        if(k >= FINDER_PATTERN_SEQUENCES.length) goto _L2; else goto _L1
_L1:
        int ai[];
        int l;
        ai = FINDER_PATTERN_SEQUENCES[k];
        if(ai.length < i)
            continue; /* Loop/switch isn't completed */
        l = 0;
_L5:
        if(l >= i)
            break MISSING_BLOCK_LABEL_166;
        if(currentSequence[l] == ai[l]) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L7:
        if(!flag)
            continue; /* Loop/switch isn't completed */
        boolean flag1;
        if(i == ai.length)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
_L4:
        l++;
          goto _L5
        k++;
          goto _L6
_L2:
        throw NotFoundException.getNotFoundInstance();
        flag = true;
          goto _L7
    }

    private static Result constructResult(Vector vector)
        throws NotFoundException
    {
        String s = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(vector)).parseInformation();
        ResultPoint aresultpoint[] = ((ExpandedPair)vector.elementAt(0)).getFinderPattern().getResultPoints();
        ResultPoint aresultpoint1[] = ((ExpandedPair)vector.lastElement()).getFinderPattern().getResultPoints();
        ResultPoint aresultpoint2[] = new ResultPoint[4];
        aresultpoint2[0] = aresultpoint[0];
        aresultpoint2[1] = aresultpoint[1];
        aresultpoint2[2] = aresultpoint1[0];
        aresultpoint2[3] = aresultpoint1[1];
        return new Result(s, null, aresultpoint2, BarcodeFormat.RSS_EXPANDED);
    }

    private void findNextPair(BitArray bitarray, Vector vector, int i)
        throws NotFoundException
    {
        int ai[] = decodeFinderCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        int j = bitarray.getSize();
        int k;
        boolean flag;
        int l;
        boolean flag1;
        if(i >= 0)
            k = i;
        else
        if(vector.isEmpty())
            k = 0;
        else
            k = ((ExpandedPair)vector.lastElement()).getFinderPattern().getStartEnd()[1];
        if(vector.size() % 2 != 0)
            flag = true;
        else
            flag = false;
        l = k;
        flag1 = false;
        do
        {
label0:
            {
label1:
                {
                    int i1;
                    boolean flag2;
                    int j1;
                    if(l < j)
                    {
                        if(!bitarray.get(l))
                            flag1 = true;
                        else
                            flag1 = false;
                        if(flag1)
                            break label1;
                    }
                    i1 = 0;
                    flag2 = flag1;
                    j1 = l;
                    while(j1 < j) 
                    {
                        if(flag2 ^ bitarray.get(j1))
                        {
                            ai[i1] = 1 + ai[i1];
                        } else
                        {
                            if(i1 == 3)
                            {
                                if(flag)
                                    reverseCounters(ai);
                                if(isFinderPattern(ai))
                                {
                                    startEnd[0] = l;
                                    startEnd[1] = j1;
                                    return;
                                }
                                if(flag)
                                    reverseCounters(ai);
                                l += ai[0] + ai[1];
                                ai[0] = ai[2];
                                ai[1] = ai[3];
                                ai[2] = 0;
                                ai[3] = 0;
                                i1--;
                            } else
                            {
                                i1++;
                            }
                            ai[i1] = 1;
                            if(!flag2)
                                flag2 = true;
                            else
                                flag2 = false;
                        }
                        j1++;
                    }
                    break label0;
                }
                l++;
                continue;
            }
            throw NotFoundException.getNotFoundInstance();
        } while(true);
    }

    private static int getNextSecondBar(BitArray bitarray, int i)
    {
        boolean flag = bitarray.get(i);
        int j;
        for(j = i; j < bitarray.size && bitarray.get(j) == flag; j++);
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        for(; j < bitarray.size && bitarray.get(j) == flag1; j++);
        return j;
    }

    private static boolean isNotA1left(FinderPattern finderpattern, boolean flag, boolean flag1)
    {
        boolean flag2;
        if(finderpattern.getValue() != 0 || !flag || !flag1)
            flag2 = true;
        else
            flag2 = false;
        return flag2;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag)
    {
        int i1;
        int j1;
        int ai[];
        int k1;
        if(flag)
        {
            int j2;
            for(j2 = startEnd[0] - 1; j2 >= 0 && !bitarray.get(j2); j2--);
            int k2 = j2 + 1;
            int l2 = startEnd[0] - k2;
            i1 = startEnd[1];
            j1 = k2;
            k1 = l2;
        } else
        {
            int j = startEnd[0];
            int k;
            for(k = 1 + startEnd[1]; bitarray.get(k) && k < bitarray.size; k++);
            int l = k - startEnd[1];
            i1 = k;
            j1 = j;
            k1 = l;
        }
        ai = decodeFinderCounters;
        for(int l1 = ai.length - 1; l1 > 0; l1--)
            ai[l1] = ai[l1 - 1];

        ai[0] = k1;
        int i2 = parseFinderValue(ai, FINDER_PATTERNS);
        FinderPattern finderpattern;
        int ai1[] = new int[2];
        ai1[0] = j1;
        ai1[1] = i1;
        finderpattern = new FinderPattern(i2, ai1, j1, i1, i);
_L2:
        return finderpattern;
        NotFoundException notfoundexception;
        notfoundexception;
        finderpattern = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void reverseCounters(int ai[])
    {
        int i = ai.length;
        for(int j = 0; j < i / 2; j++)
        {
            int k = ai[j];
            ai[j] = ai[i - j - 1];
            ai[i - j - 1] = k;
        }

    }

    DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag, boolean flag1)
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
        float f;
        int ai1[];
        int ai2[];
        float af[];
        float af1[];
        int i1;
        if(flag1)
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
        f = (float)count(ai) / (float)17;
        ai1 = oddCounts;
        ai2 = evenCounts;
        af = oddRoundingErrors;
        af1 = evenRoundingErrors;
        i1 = 0;
        do
        {
            if(i1 >= ai.length)
                break;
            float f1 = (1.0F * (float)ai[i1]) / f;
            int j6 = (int)(0.5F + f1);
            int k6;
            if(j6 < 1)
                j6 = 1;
            else
            if(j6 > 8)
                j6 = 8;
            k6 = i1 >> 1;
            if((i1 & 1) == 0)
            {
                ai1[k6] = j6;
                af[k6] = f1 - (float)j6;
            } else
            {
                ai2[k6] = j6;
                af1[k6] = f1 - (float)j6;
            }
            i1++;
        } while(true);
        adjustOddEvenCounts(17);
        int j1 = 4 * finderpattern.getValue();
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        if(flag)
            k1 = 0;
        else
            k1 = 2;
        l1 = j1 + k1;
        if(flag1)
            i2 = 0;
        else
            i2 = 1;
        j2 = (l1 + i2) - 1;
        k2 = 0;
        l2 = ai1.length - 1;
        i3 = 0;
        for(int j3 = l2; j3 >= 0; j3--)
        {
            if(isNotA1left(finderpattern, flag, flag1))
                k2 += WEIGHTS[j2][j3 * 2] * ai1[j3];
            i3 += ai1[j3];
        }

        int k3 = 0;
        int l3 = ai2.length - 1;
        int i4 = 0;
        for(int j4 = l3; j4 >= 0; j4--)
        {
            if(isNotA1left(finderpattern, flag, flag1))
                i4 += WEIGHTS[j2][1 + j4 * 2] * ai2[j4];
            k3 += ai2[j4];
        }

        int k4 = k2 + i4;
        if((i3 & 1) != 0 || i3 > 13 || i3 < 4)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            int l4 = (13 - i3) / 2;
            int i5 = SYMBOL_WIDEST[l4];
            int j5 = 9 - i5;
            int k5 = RSSUtils.getRSSvalue(ai1, i5, true);
            int l5 = RSSUtils.getRSSvalue(ai2, j5, false);
            int i6 = EVEN_TOTAL_SUBSET[l4];
            return new DataCharacter(GSUM[l4] + (l5 + k5 * i6), k4);
        }
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        reset();
        decodeRow2pairs(i, bitarray);
        return constructResult(pairs);
    }

    Vector decodeRow2pairs(int i, BitArray bitarray)
        throws NotFoundException
    {
        ExpandedPair expandedpair;
        do
        {
            do
            {
                expandedpair = retrieveNextPair(bitarray, pairs, i);
                pairs.addElement(expandedpair);
            } while(!expandedpair.mayBeLast());
            if(checkChecksum())
                return pairs;
        } while(!expandedpair.mustBeLast());
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset()
    {
        pairs.setSize(0);
    }

    ExpandedPair retrieveNextPair(BitArray bitarray, Vector vector, int i)
        throws NotFoundException
    {
        boolean flag2;
        DataCharacter datacharacter1;
        boolean flag;
        int j;
        boolean flag1;
        if(vector.size() % 2 == 0)
            flag = true;
        else
            flag = false;
        j = -1;
        flag1 = true;
        do
        {
            findNextPair(bitarray, vector, j);
            FinderPattern finderpattern = parseFoundFinderPattern(bitarray, i, flag);
            DataCharacter datacharacter;
            DataCharacter datacharacter2;
            if(finderpattern == null)
                j = getNextSecondBar(bitarray, startEnd[0]);
            else
                flag1 = false;
        } while(flag1);
        flag2 = checkPairSequence(vector, finderpattern);
        datacharacter = decodeDataCharacter(bitarray, finderpattern, flag, true);
        datacharacter2 = decodeDataCharacter(bitarray, finderpattern, flag, false);
        datacharacter1 = datacharacter2;
_L2:
        return new ExpandedPair(datacharacter, datacharacter1, finderpattern, flag2);
        NotFoundException notfoundexception;
        notfoundexception;
        if(flag2)
            datacharacter1 = null;
        else
            throw notfoundexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int EVEN_TOTAL_SUBSET[];
    private static final int FINDER_PATTERNS[][];
    private static final int FINDER_PATTERN_SEQUENCES[][];
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int GSUM[];
    private static final int LONGEST_SEQUENCE_SIZE = 0;
    private static final int MAX_PAIRS = 11;
    private static final int SYMBOL_WIDEST[];
    private static final int WEIGHTS[][];
    private final int currentSequence[];
    private final Vector pairs = new Vector(11);
    private final int startEnd[] = new int[2];

    static 
    {
        int ai[] = new int[5];
        ai[0] = 7;
        ai[1] = 5;
        ai[2] = 4;
        ai[3] = 3;
        ai[4] = 1;
        SYMBOL_WIDEST = ai;
        int ai1[] = new int[5];
        ai1[0] = 4;
        ai1[1] = 20;
        ai1[2] = 52;
        ai1[3] = 104;
        ai1[4] = 204;
        EVEN_TOTAL_SUBSET = ai1;
        int ai2[] = new int[5];
        ai2[0] = 0;
        ai2[1] = 348;
        ai2[2] = 1388;
        ai2[3] = 2948;
        ai2[4] = 3988;
        GSUM = ai2;
        int ai3[][] = new int[6][];
        int ai4[] = new int[4];
        ai4[0] = 1;
        ai4[1] = 8;
        ai4[2] = 4;
        ai4[3] = 1;
        ai3[0] = ai4;
        int ai5[] = new int[4];
        ai5[0] = 3;
        ai5[1] = 6;
        ai5[2] = 4;
        ai5[3] = 1;
        ai3[1] = ai5;
        int ai6[] = new int[4];
        ai6[0] = 3;
        ai6[1] = 4;
        ai6[2] = 6;
        ai6[3] = 1;
        ai3[2] = ai6;
        int ai7[] = new int[4];
        ai7[0] = 3;
        ai7[1] = 2;
        ai7[2] = 8;
        ai7[3] = 1;
        ai3[3] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 2;
        ai8[1] = 6;
        ai8[2] = 5;
        ai8[3] = 1;
        ai3[4] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 2;
        ai9[1] = 2;
        ai9[2] = 9;
        ai9[3] = 1;
        ai3[5] = ai9;
        FINDER_PATTERNS = ai3;
        int ai10[][] = new int[23][];
        int ai11[] = new int[8];
        ai11[0] = 1;
        ai11[1] = 3;
        ai11[2] = 9;
        ai11[3] = 27;
        ai11[4] = 81;
        ai11[5] = 32;
        ai11[6] = 96;
        ai11[7] = 77;
        ai10[0] = ai11;
        int ai12[] = new int[8];
        ai12[0] = 20;
        ai12[1] = 60;
        ai12[2] = 180;
        ai12[3] = 118;
        ai12[4] = 143;
        ai12[5] = 7;
        ai12[6] = 21;
        ai12[7] = 63;
        ai10[1] = ai12;
        int ai13[] = new int[8];
        ai13[0] = 189;
        ai13[1] = 145;
        ai13[2] = 13;
        ai13[3] = 39;
        ai13[4] = 117;
        ai13[5] = 140;
        ai13[6] = 209;
        ai13[7] = 205;
        ai10[2] = ai13;
        int ai14[] = new int[8];
        ai14[0] = 193;
        ai14[1] = 157;
        ai14[2] = 49;
        ai14[3] = 147;
        ai14[4] = 19;
        ai14[5] = 57;
        ai14[6] = 171;
        ai14[7] = 91;
        ai10[3] = ai14;
        int ai15[] = new int[8];
        ai15[0] = 62;
        ai15[1] = 186;
        ai15[2] = 136;
        ai15[3] = 197;
        ai15[4] = 169;
        ai15[5] = 85;
        ai15[6] = 44;
        ai15[7] = 132;
        ai10[4] = ai15;
        int ai16[] = new int[8];
        ai16[0] = 185;
        ai16[1] = 133;
        ai16[2] = 188;
        ai16[3] = 142;
        ai16[4] = 4;
        ai16[5] = 12;
        ai16[6] = 36;
        ai16[7] = 108;
        ai10[5] = ai16;
        int ai17[] = new int[8];
        ai17[0] = 113;
        ai17[1] = 128;
        ai17[2] = 173;
        ai17[3] = 97;
        ai17[4] = 80;
        ai17[5] = 29;
        ai17[6] = 87;
        ai17[7] = 50;
        ai10[6] = ai17;
        int ai18[] = new int[8];
        ai18[0] = 150;
        ai18[1] = 28;
        ai18[2] = 84;
        ai18[3] = 41;
        ai18[4] = 123;
        ai18[5] = 158;
        ai18[6] = 52;
        ai18[7] = 156;
        ai10[7] = ai18;
        int ai19[] = new int[8];
        ai19[0] = 46;
        ai19[1] = 138;
        ai19[2] = 203;
        ai19[3] = 187;
        ai19[4] = 139;
        ai19[5] = 206;
        ai19[6] = 196;
        ai19[7] = 166;
        ai10[8] = ai19;
        int ai20[] = new int[8];
        ai20[0] = 76;
        ai20[1] = 17;
        ai20[2] = 51;
        ai20[3] = 153;
        ai20[4] = 37;
        ai20[5] = 111;
        ai20[6] = 122;
        ai20[7] = 155;
        ai10[9] = ai20;
        int ai21[] = new int[8];
        ai21[0] = 43;
        ai21[1] = 129;
        ai21[2] = 176;
        ai21[3] = 106;
        ai21[4] = 107;
        ai21[5] = 110;
        ai21[6] = 119;
        ai21[7] = 146;
        ai10[10] = ai21;
        int ai22[] = new int[8];
        ai22[0] = 16;
        ai22[1] = 48;
        ai22[2] = 144;
        ai22[3] = 10;
        ai22[4] = 30;
        ai22[5] = 90;
        ai22[6] = 59;
        ai22[7] = 177;
        ai10[11] = ai22;
        int ai23[] = new int[8];
        ai23[0] = 109;
        ai23[1] = 116;
        ai23[2] = 137;
        ai23[3] = 200;
        ai23[4] = 178;
        ai23[5] = 112;
        ai23[6] = 125;
        ai23[7] = 164;
        ai10[12] = ai23;
        int ai24[] = new int[8];
        ai24[0] = 70;
        ai24[1] = 210;
        ai24[2] = 208;
        ai24[3] = 202;
        ai24[4] = 184;
        ai24[5] = 130;
        ai24[6] = 179;
        ai24[7] = 115;
        ai10[13] = ai24;
        int ai25[] = new int[8];
        ai25[0] = 134;
        ai25[1] = 191;
        ai25[2] = 151;
        ai25[3] = 31;
        ai25[4] = 93;
        ai25[5] = 68;
        ai25[6] = 204;
        ai25[7] = 190;
        ai10[14] = ai25;
        int ai26[] = new int[8];
        ai26[0] = 148;
        ai26[1] = 22;
        ai26[2] = 66;
        ai26[3] = 198;
        ai26[4] = 172;
        ai26[5] = 94;
        ai26[6] = 71;
        ai26[7] = 2;
        ai10[15] = ai26;
        int ai27[] = new int[8];
        ai27[0] = 6;
        ai27[1] = 18;
        ai27[2] = 54;
        ai27[3] = 162;
        ai27[4] = 64;
        ai27[5] = 192;
        ai27[6] = 154;
        ai27[7] = 40;
        ai10[16] = ai27;
        int ai28[] = new int[8];
        ai28[0] = 120;
        ai28[1] = 149;
        ai28[2] = 25;
        ai28[3] = 75;
        ai28[4] = 14;
        ai28[5] = 42;
        ai28[6] = 126;
        ai28[7] = 167;
        ai10[17] = ai28;
        int ai29[] = new int[8];
        ai29[0] = 79;
        ai29[1] = 26;
        ai29[2] = 78;
        ai29[3] = 23;
        ai29[4] = 69;
        ai29[5] = 207;
        ai29[6] = 199;
        ai29[7] = 175;
        ai10[18] = ai29;
        int ai30[] = new int[8];
        ai30[0] = 103;
        ai30[1] = 98;
        ai30[2] = 83;
        ai30[3] = 38;
        ai30[4] = 114;
        ai30[5] = 131;
        ai30[6] = 182;
        ai30[7] = 124;
        ai10[19] = ai30;
        int ai31[] = new int[8];
        ai31[0] = 161;
        ai31[1] = 61;
        ai31[2] = 183;
        ai31[3] = 127;
        ai31[4] = 170;
        ai31[5] = 88;
        ai31[6] = 53;
        ai31[7] = 159;
        ai10[20] = ai31;
        int ai32[] = new int[8];
        ai32[0] = 55;
        ai32[1] = 165;
        ai32[2] = 73;
        ai32[3] = 8;
        ai32[4] = 24;
        ai32[5] = 72;
        ai32[6] = 5;
        ai32[7] = 15;
        ai10[21] = ai32;
        int ai33[] = new int[8];
        ai33[0] = 45;
        ai33[1] = 135;
        ai33[2] = 194;
        ai33[3] = 160;
        ai33[4] = 58;
        ai33[5] = 174;
        ai33[6] = 100;
        ai33[7] = 89;
        ai10[22] = ai33;
        WEIGHTS = ai10;
        int ai34[][] = new int[10][];
        int ai35[] = new int[2];
        ai35[0] = 0;
        ai35[1] = 0;
        ai34[0] = ai35;
        int ai36[] = new int[3];
        ai36[0] = 0;
        ai36[1] = 1;
        ai36[2] = 1;
        ai34[1] = ai36;
        int ai37[] = new int[4];
        ai37[0] = 0;
        ai37[1] = 2;
        ai37[2] = 1;
        ai37[3] = 3;
        ai34[2] = ai37;
        int ai38[] = new int[5];
        ai38[0] = 0;
        ai38[1] = 4;
        ai38[2] = 1;
        ai38[3] = 3;
        ai38[4] = 2;
        ai34[3] = ai38;
        int ai39[] = new int[6];
        ai39[0] = 0;
        ai39[1] = 4;
        ai39[2] = 1;
        ai39[3] = 3;
        ai39[4] = 3;
        ai39[5] = 5;
        ai34[4] = ai39;
        int ai40[] = new int[7];
        ai40[0] = 0;
        ai40[1] = 4;
        ai40[2] = 1;
        ai40[3] = 3;
        ai40[4] = 4;
        ai40[5] = 5;
        ai40[6] = 5;
        ai34[5] = ai40;
        int ai41[] = new int[8];
        ai41[0] = 0;
        ai41[1] = 0;
        ai41[2] = 1;
        ai41[3] = 1;
        ai41[4] = 2;
        ai41[5] = 2;
        ai41[6] = 3;
        ai41[7] = 3;
        ai34[6] = ai41;
        int ai42[] = new int[9];
        ai42[0] = 0;
        ai42[1] = 0;
        ai42[2] = 1;
        ai42[3] = 1;
        ai42[4] = 2;
        ai42[5] = 2;
        ai42[6] = 3;
        ai42[7] = 4;
        ai42[8] = 4;
        ai34[7] = ai42;
        int ai43[] = new int[10];
        ai43[0] = 0;
        ai43[1] = 0;
        ai43[2] = 1;
        ai43[3] = 1;
        ai43[4] = 2;
        ai43[5] = 2;
        ai43[6] = 3;
        ai43[7] = 4;
        ai43[8] = 5;
        ai43[9] = 5;
        ai34[8] = ai43;
        int ai44[] = new int[11];
        ai44[0] = 0;
        ai44[1] = 0;
        ai44[2] = 1;
        ai44[3] = 1;
        ai44[4] = 2;
        ai44[5] = 3;
        ai44[6] = 3;
        ai44[7] = 4;
        ai44[8] = 4;
        ai44[9] = 5;
        ai44[10] = 5;
        ai34[9] = ai44;
        FINDER_PATTERN_SEQUENCES = ai34;
        LONGEST_SEQUENCE_SIZE = FINDER_PATTERN_SEQUENCES[FINDER_PATTERN_SEQUENCES.length - 1].length;
    }
}
