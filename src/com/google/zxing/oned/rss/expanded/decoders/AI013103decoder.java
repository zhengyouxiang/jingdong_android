// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AI013x0xDecoder

final class AI013103decoder extends AI013x0xDecoder
{

    AI013103decoder(BitArray bitarray)
    {
        super(bitarray);
    }

    protected void addWeightCode(StringBuffer stringbuffer, int i)
    {
        stringbuffer.append("(3103)");
    }

    protected int checkWeight(int i)
    {
        return i;
    }
}
