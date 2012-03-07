// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, WifiParsedResult

final class WifiResultParser extends ResultParser
{

    private WifiResultParser()
    {
    }

    public static WifiParsedResult parse(Result result)
    {
        String s = result.getText();
        WifiParsedResult wifiparsedresult;
        if(s == null || !s.startsWith("WIFI:"))
        {
            wifiparsedresult = null;
        } else
        {
            String s1 = matchSinglePrefixedField("S:", s, ';', false);
            String s2 = matchSinglePrefixedField("P:", s, ';', false);
            wifiparsedresult = new WifiParsedResult(matchSinglePrefixedField("T:", s, ';', false), s1, s2);
        }
        return wifiparsedresult;
    }
}
