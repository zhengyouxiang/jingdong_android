// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedInformation

final class BlockParsedResult
{

    BlockParsedResult()
    {
        finished = true;
        decodedInformation = null;
    }

    BlockParsedResult(DecodedInformation decodedinformation, boolean flag)
    {
        finished = flag;
        decodedInformation = decodedinformation;
    }

    BlockParsedResult(boolean flag)
    {
        finished = flag;
        decodedInformation = null;
    }

    DecodedInformation getDecodedInformation()
    {
        return decodedInformation;
    }

    boolean isFinished()
    {
        return finished;
    }

    private final DecodedInformation decodedInformation;
    private final boolean finished;
}
