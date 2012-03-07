// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, GeneralAppIdDecoder

abstract class AI01decoder extends AbstractExpandedDecoder
{

    AI01decoder(BitArray bitarray)
    {
        super(bitarray);
    }

    private static void appendCheckDigit(StringBuffer stringbuffer, int i)
    {
        int j = 0;
        int k = 0;
        for(; j < 13; j++)
        {
            int i1 = stringbuffer.charAt(j + i) - 48;
            if((j & 1) == 0)
                i1 *= 3;
            k += i1;
        }

        int l = 10 - k % 10;
        if(l == 10)
            l = 0;
        stringbuffer.append(l);
    }

    protected void encodeCompressedGtin(StringBuffer stringbuffer, int i)
    {
        stringbuffer.append("(01)");
        int j = stringbuffer.length();
        stringbuffer.append('9');
        encodeCompressedGtinWithoutAI(stringbuffer, i, j);
    }

    protected void encodeCompressedGtinWithoutAI(StringBuffer stringbuffer, int i, int j)
    {
        for(int k = 0; k < 4; k++)
        {
            int l = generalDecoder.extractNumericValueFromBitArray(i + k * 10, 10);
            if(l / 100 == 0)
                stringbuffer.append('0');
            if(l / 10 == 0)
                stringbuffer.append('0');
            stringbuffer.append(l);
        }

        appendCheckDigit(stringbuffer, j);
    }

    protected static final int gtinSize = 40;
}
