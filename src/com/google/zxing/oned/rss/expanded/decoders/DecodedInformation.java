// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedInformation extends DecodedObject
{

    DecodedInformation(int i, String s)
    {
        super(i);
        newString = s;
        remaining = false;
        remainingValue = 0;
    }

    DecodedInformation(int i, String s, int j)
    {
        super(i);
        remaining = true;
        remainingValue = j;
        newString = s;
    }

    String getNewString()
    {
        return newString;
    }

    int getRemainingValue()
    {
        return remainingValue;
    }

    boolean isRemaining()
    {
        return remaining;
    }

    private final String newString;
    private final boolean remaining;
    private final int remainingValue;
}
