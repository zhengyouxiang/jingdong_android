// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN13Reader extends UPCEANReader
{

    public EAN13Reader()
    {
    }

    private static void determineFirstDigit(StringBuffer stringbuffer, int i)
        throws NotFoundException
    {
        for(int j = 0; j < 10; j++)
            if(i == FIRST_DIGIT_ENCODINGS[j])
            {
                stringbuffer.insert(0, (char)(j + 48));
                return;
            }

        throw NotFoundException.getNotFoundInstance();
    }

    protected int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai1[] = decodeMiddleCounters;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        int i = bitarray.getSize();
        int j = ai[1];
        int k = 0;
        int l = j;
        int k2;
        for(int i1 = 0; i1 < 6 && l < i; l = k2)
        {
            int j2 = decodeDigit(bitarray, ai1, l, L_AND_G_PATTERNS);
            stringbuffer.append((char)(48 + j2 % 10));
            k2 = l;
            for(int l2 = 0; l2 < ai1.length; l2++)
                k2 += ai1[l2];

            if(j2 >= 10)
                k |= 1 << 5 - i1;
            i1++;
        }

        determineFirstDigit(stringbuffer, k);
        int j1 = findGuardPattern(bitarray, l, true, MIDDLE_PATTERN)[1];
        int l1;
        for(int k1 = 0; k1 < 6 && j1 < i; j1 = l1)
        {
            stringbuffer.append((char)(48 + decodeDigit(bitarray, ai1, j1, L_PATTERNS)));
            l1 = j1;
            for(int i2 = 0; i2 < ai1.length; i2++)
                l1 += ai1[i2];

            k1++;
        }

        return j1;
    }

    BarcodeFormat getBarcodeFormat()
    {
        return BarcodeFormat.EAN_13;
    }

    static final int FIRST_DIGIT_ENCODINGS[];
    private final int decodeMiddleCounters[] = new int[4];

    static 
    {
        int ai[] = new int[10];
        ai[0] = 0;
        ai[1] = 11;
        ai[2] = 13;
        ai[3] = 14;
        ai[4] = 19;
        ai[5] = 25;
        ai[6] = 28;
        ai[7] = 21;
        ai[8] = 22;
        ai[9] = 26;
        FIRST_DIGIT_ENCODINGS = ai;
    }
}
