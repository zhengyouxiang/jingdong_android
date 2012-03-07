// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, UPCEANExtensionSupport, EANManufacturerOrgSupport

public abstract class UPCEANReader extends OneDReader
{

    protected UPCEANReader()
    {
    }

    private static boolean checkStandardUPCEANChecksum(String s)
        throws FormatException
    {
        int i = s.length();
        boolean flag;
        if(i == 0)
        {
            flag = false;
        } else
        {
            int j = i - 2;
            int k = 0;
            for(; j >= 0; j += -2)
            {
                int k1 = s.charAt(j) - 48;
                if(k1 < 0 || k1 > 9)
                    throw FormatException.getFormatInstance();
                k += k1;
            }

            int l = k * 3;
            for(int i1 = i - 1; i1 >= 0; i1 += -2)
            {
                int j1 = s.charAt(i1) - 48;
                if(j1 < 0 || j1 > 9)
                    throw FormatException.getFormatInstance();
                l += j1;
            }

            if(l % 10 == 0)
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    static int decodeDigit(BitArray bitarray, int ai[], int i, int ai1[][])
        throws NotFoundException
    {
        recordPattern(bitarray, i, ai);
        int j = -1;
        int k = ai1.length;
        int l = 107;
        for(int i1 = 0; i1 < k; i1++)
        {
            int j1 = patternMatchVariance(ai, ai1[i1], 179);
            if(j1 < l)
            {
                j = i1;
                l = j1;
            }
        }

        if(j >= 0)
            return j;
        else
            throw NotFoundException.getNotFoundInstance();
    }

    static int[] findGuardPattern(BitArray bitarray, int i, boolean flag, int ai[])
        throws NotFoundException
    {
        int j = ai.length;
        int ai1[] = new int[j];
        int k = bitarray.getSize();
        int l = i;
        boolean flag1 = false;
        do
        {
label0:
            {
label1:
                {
                    boolean flag2;
                    int i1;
                    int j1;
                    int k1;
                    if(l < k)
                    {
                        if(!bitarray.get(l))
                            flag1 = true;
                        else
                            flag1 = false;
                        if(flag != flag1)
                            break label1;
                    }
                    flag2 = flag1;
                    i1 = l;
                    j1 = l;
                    k1 = 0;
                    while(i1 < k) 
                    {
                        if(flag2 ^ bitarray.get(i1))
                        {
                            ai1[k1] = 1 + ai1[k1];
                        } else
                        {
                            if(k1 == j - 1)
                            {
                                if(patternMatchVariance(ai1, ai, 179) < 107)
                                {
                                    int ai2[] = new int[2];
                                    ai2[0] = j1;
                                    ai2[1] = i1;
                                    return ai2;
                                }
                                j1 += ai1[0] + ai1[1];
                                for(int l1 = 2; l1 < j; l1++)
                                    ai1[l1 - 2] = ai1[l1];

                                ai1[j - 2] = 0;
                                ai1[j - 1] = 0;
                                k1--;
                            } else
                            {
                                k1++;
                            }
                            ai1[k1] = 1;
                            if(!flag2)
                                flag2 = true;
                            else
                                flag2 = false;
                        }
                        i1++;
                    }
                    break label0;
                }
                l++;
                continue;
            }
            throw NotFoundException.getNotFoundInstance();
        } while(true);
    }

    static int[] findStartGuardPattern(BitArray bitarray)
        throws NotFoundException
    {
        int ai[] = null;
        boolean flag = false;
        int i = 0;
        while(!flag) 
        {
            int ai1[] = findGuardPattern(bitarray, i, false, START_END_PATTERN);
            int j = ai1[0];
            int k = ai1[1];
            int l = j - (k - j);
            boolean flag1;
            if(l >= 0)
                flag1 = bitarray.isRange(l, j, false);
            else
                flag1 = flag;
            flag = flag1;
            ai = ai1;
            i = k;
        }
        return ai;
    }

    boolean checkChecksum(String s)
        throws ChecksumException, FormatException
    {
        return checkStandardUPCEANChecksum(s);
    }

    int[] decodeEnd(BitArray bitarray, int i)
        throws NotFoundException
    {
        return findGuardPattern(bitarray, i, false, START_END_PATTERN);
    }

    protected abstract int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException;

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decodeRow(i, bitarray, findStartGuardPattern(bitarray), hashtable);
    }

    public Result decodeRow(int i, BitArray bitarray, int ai[], Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        ResultPointCallback resultpointcallback;
        StringBuffer stringbuffer;
        int j;
        int ai1[];
        int k;
        int l;
        if(hashtable == null)
            resultpointcallback = null;
        else
            resultpointcallback = (ResultPointCallback)hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        if(resultpointcallback != null)
            resultpointcallback.foundPossibleResultPoint(new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i));
        stringbuffer = decodeRowStringBuffer;
        stringbuffer.setLength(0);
        j = decodeMiddle(bitarray, ai, stringbuffer);
        if(resultpointcallback != null)
            resultpointcallback.foundPossibleResultPoint(new ResultPoint(j, i));
        ai1 = decodeEnd(bitarray, j);
        if(resultpointcallback != null)
            resultpointcallback.foundPossibleResultPoint(new ResultPoint((float)(ai1[0] + ai1[1]) / 2.0F, i));
        k = ai1[1];
        l = k + (k - ai1[0]);
        if(l >= bitarray.getSize() || !bitarray.isRange(k, l, false))
            throw NotFoundException.getNotFoundInstance();
        String s = stringbuffer.toString();
        if(!checkChecksum(s))
            throw ChecksumException.getChecksumInstance();
        float f = (float)(ai[1] + ai[0]) / 2.0F;
        float f1 = (float)(ai1[1] + ai1[0]) / 2.0F;
        BarcodeFormat barcodeformat = getBarcodeFormat();
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint(f, i);
        aresultpoint[1] = new ResultPoint(f1, i);
        Result result = new Result(s, null, aresultpoint, barcodeformat);
        try
        {
            Result result1 = extensionReader.decodeRow(i, bitarray, ai1[1]);
            result.putAllMetadata(result1.getResultMetadata());
            result.addResultPoints(result1.getResultPoints());
        }
        catch(ReaderException readerexception) { }
        if(BarcodeFormat.EAN_13.equals(barcodeformat) || BarcodeFormat.UPC_A.equals(barcodeformat))
        {
            String s1 = eanManSupport.lookupCountryIdentifier(s);
            if(s1 != null)
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, s1);
        }
        return result;
    }

    abstract BarcodeFormat getBarcodeFormat();

    static final int L_AND_G_PATTERNS[][];
    static final int L_PATTERNS[][];
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;
    static final int MIDDLE_PATTERN[];
    static final int START_END_PATTERN[];
    private final StringBuffer decodeRowStringBuffer = new StringBuffer(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    static 
    {
        int ai[] = new int[3];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 1;
        START_END_PATTERN = ai;
        int ai1[] = new int[5];
        ai1[0] = 1;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        ai1[4] = 1;
        MIDDLE_PATTERN = ai1;
        int ai2[][] = new int[10][];
        int ai3[] = new int[4];
        ai3[0] = 3;
        ai3[1] = 2;
        ai3[2] = 1;
        ai3[3] = 1;
        ai2[0] = ai3;
        int ai4[] = new int[4];
        ai4[0] = 2;
        ai4[1] = 2;
        ai4[2] = 2;
        ai4[3] = 1;
        ai2[1] = ai4;
        int ai5[] = new int[4];
        ai5[0] = 2;
        ai5[1] = 1;
        ai5[2] = 2;
        ai5[3] = 2;
        ai2[2] = ai5;
        int ai6[] = new int[4];
        ai6[0] = 1;
        ai6[1] = 4;
        ai6[2] = 1;
        ai6[3] = 1;
        ai2[3] = ai6;
        int ai7[] = new int[4];
        ai7[0] = 1;
        ai7[1] = 1;
        ai7[2] = 3;
        ai7[3] = 2;
        ai2[4] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 1;
        ai8[1] = 2;
        ai8[2] = 3;
        ai8[3] = 1;
        ai2[5] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 1;
        ai9[1] = 1;
        ai9[2] = 1;
        ai9[3] = 4;
        ai2[6] = ai9;
        int ai10[] = new int[4];
        ai10[0] = 1;
        ai10[1] = 3;
        ai10[2] = 1;
        ai10[3] = 2;
        ai2[7] = ai10;
        int ai11[] = new int[4];
        ai11[0] = 1;
        ai11[1] = 2;
        ai11[2] = 1;
        ai11[3] = 3;
        ai2[8] = ai11;
        int ai12[] = new int[4];
        ai12[0] = 3;
        ai12[1] = 1;
        ai12[2] = 1;
        ai12[3] = 2;
        ai2[9] = ai12;
        L_PATTERNS = ai2;
        L_AND_G_PATTERNS = new int[20][];
        for(int i = 0; i < 10; i++)
            L_AND_G_PATTERNS[i] = L_PATTERNS[i];

        for(int j = 10; j < 20; j++)
        {
            int ai13[] = L_PATTERNS[j - 10];
            int ai14[] = new int[ai13.length];
            for(int k = 0; k < ai13.length; k++)
                ai14[k] = ai13[ai13.length - k - 1];

            L_AND_G_PATTERNS[j] = ai14;
        }

    }
}
