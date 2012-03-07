// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI01weightDecoder

abstract class AI013x0xDecoder extends AI01weightDecoder
{

    AI013x0xDecoder(BitArray bitarray)
    {
        super(bitarray);
    }

    public String parseInformation()
        throws NotFoundException
    {
        if(information.size != 60)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            encodeCompressedGtin(stringbuffer, 5);
            encodeCompressedWeight(stringbuffer, 45, 15);
            return stringbuffer.toString();
        }
    }

    private static final int headerSize = 5;
    private static final int weightSize = 15;
}
