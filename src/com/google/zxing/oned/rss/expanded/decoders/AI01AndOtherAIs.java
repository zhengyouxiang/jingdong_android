// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, GeneralAppIdDecoder

final class AI01AndOtherAIs extends AI01decoder
{

    AI01AndOtherAIs(BitArray bitarray)
    {
        super(bitarray);
    }

    public String parseInformation()
        throws NotFoundException
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("(01)");
        int i = stringbuffer.length();
        stringbuffer.append(generalDecoder.extractNumericValueFromBitArray(4, 4));
        encodeCompressedGtinWithoutAI(stringbuffer, 8, i);
        return generalDecoder.decodeAllCodes(stringbuffer, 48);
    }

    private static final int HEADER_SIZE = 4;
}
