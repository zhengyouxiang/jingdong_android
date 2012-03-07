// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, GeneralAppIdDecoder

abstract class AI01weightDecoder extends AI01decoder
{

    AI01weightDecoder(BitArray bitarray)
    {
        super(bitarray);
    }

    protected abstract void addWeightCode(StringBuffer stringbuffer, int i);

    protected abstract int checkWeight(int i);

    protected void encodeCompressedWeight(StringBuffer stringbuffer, int i, int j)
    {
        int k = generalDecoder.extractNumericValueFromBitArray(i, j);
        addWeightCode(stringbuffer, k);
        int l = checkWeight(k);
        int i1 = 0x186a0;
        for(int j1 = 0; j1 < 5; j1++)
        {
            if(l / i1 == 0)
                stringbuffer.append('0');
            i1 /= 10;
        }

        stringbuffer.append(l);
    }
}
