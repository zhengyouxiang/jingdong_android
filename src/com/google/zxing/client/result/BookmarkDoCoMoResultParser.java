// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, URIResultParser, URIParsedResult

final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser
{

    private BookmarkDoCoMoResultParser()
    {
    }

    public static URIParsedResult parse(Result result)
    {
        String s = result.getText();
        URIParsedResult uriparsedresult;
        if(s == null || !s.startsWith("MEBKM:"))
        {
            uriparsedresult = null;
        } else
        {
            String s1 = matchSingleDoCoMoPrefixedField("TITLE:", s, true);
            String as[] = matchDoCoMoPrefixedField("URL:", s, true);
            if(as == null)
            {
                uriparsedresult = null;
            } else
            {
                String s2 = as[0];
                if(!URIResultParser.isBasicallyValidURI(s2))
                    uriparsedresult = null;
                else
                    uriparsedresult = new URIParsedResult(s2, s1);
            }
        }
        return uriparsedresult;
    }
}
