// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01decoder, GeneralAppIdDecoder, DecodedInformation

final class AI01393xDecoder extends AI01decoder
{

    AI01393xDecoder(BitArray bitarray)
    {
        super(bitarray);
    }

    public String parseInformation()
        throws NotFoundException
    {
        if(information.size < 48)
            throw NotFoundException.getNotFoundInstance();
        StringBuffer stringbuffer = new StringBuffer();
        encodeCompressedGtin(stringbuffer, 8);
        int i = generalDecoder.extractNumericValueFromBitArray(48, 2);
        stringbuffer.append("(393");
        stringbuffer.append(i);
        stringbuffer.append(')');
        int j = generalDecoder.extractNumericValueFromBitArray(50, 10);
        if(j / 100 == 0)
            stringbuffer.append('0');
        if(j / 10 == 0)
            stringbuffer.append('0');
        stringbuffer.append(j);
        stringbuffer.append(generalDecoder.decodeGeneralPurposeField(60, null).getNewString());
        return stringbuffer.toString();
    }

    private static final int firstThreeDigitsSize = 10;
    private static final int headerSize = 8;
    private static final int lastDigitSize = 2;
}
