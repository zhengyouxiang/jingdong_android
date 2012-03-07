// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            URIParsedResult

final class URLTOResultParser
{

    private URLTOResultParser()
    {
    }

    public static URIParsedResult parse(Result result)
    {
        String s = result.getText();
        URIParsedResult uriparsedresult;
        if(s == null || !s.startsWith("urlto:") && !s.startsWith("URLTO:"))
        {
            uriparsedresult = null;
        } else
        {
            int i = s.indexOf(':', 6);
            if(i < 0)
            {
                uriparsedresult = null;
            } else
            {
                String s1;
                if(i <= 6)
                    s1 = null;
                else
                    s1 = s.substring(6, i);
                uriparsedresult = new URIParsedResult(s.substring(i + 1), s1);
            }
        }
        return uriparsedresult;
    }
}
