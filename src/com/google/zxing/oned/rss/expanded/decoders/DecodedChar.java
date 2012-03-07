// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedChar extends DecodedObject
{

    DecodedChar(int i, char c)
    {
        super(i);
        value = c;
    }

    char getValue()
    {
        return value;
    }

    boolean isFNC1()
    {
        boolean flag;
        if(value == '$')
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final char FNC1 = 36;
    private final char value;
}
