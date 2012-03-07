// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, TelParsedResult

final class TelResultParser extends ResultParser
{

    private TelResultParser()
    {
    }

    public static TelParsedResult parse(Result result)
    {
        String s = result.getText();
        TelParsedResult telparsedresult;
        if(s == null || !s.startsWith("tel:") && !s.startsWith("TEL:"))
        {
            telparsedresult = null;
        } else
        {
            String s1;
            int i;
            String s2;
            if(s.startsWith("TEL:"))
                s1 = "tel:" + s.substring(4);
            else
                s1 = s;
            i = s.indexOf('?', 4);
            if(i < 0)
                s2 = s.substring(4);
            else
                s2 = s.substring(4, i);
            telparsedresult = new TelParsedResult(s2, s1, null);
        }
        return telparsedresult;
    }
}
