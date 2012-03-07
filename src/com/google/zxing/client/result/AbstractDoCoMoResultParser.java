// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ResultParser

abstract class AbstractDoCoMoResultParser extends ResultParser
{

    AbstractDoCoMoResultParser()
    {
    }

    static String[] matchDoCoMoPrefixedField(String s, String s1, boolean flag)
    {
        return matchPrefixedField(s, s1, ';', flag);
    }

    static String matchSingleDoCoMoPrefixedField(String s, String s1, boolean flag)
    {
        return matchSinglePrefixedField(s, s1, ';', flag);
    }
}
