// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, GeneralAppIdDecoder

final class AnyAIDecoder extends AbstractExpandedDecoder
{

    AnyAIDecoder(BitArray bitarray)
    {
        super(bitarray);
    }

    public String parseInformation()
        throws NotFoundException
    {
        StringBuffer stringbuffer = new StringBuffer();
        return generalDecoder.decodeAllCodes(stringbuffer, 5);
    }

    private static final int HEADER_SIZE = 5;
}
