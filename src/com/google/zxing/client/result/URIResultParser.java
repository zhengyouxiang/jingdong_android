// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, URIParsedResult

final class URIResultParser extends ResultParser
{

    private URIResultParser()
    {
    }

    static boolean isBasicallyValidURI(String s)
    {
        if(s != null && s.indexOf(' ') < 0 && s.indexOf('\n') < 0) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i = s.indexOf('.');
        if(i >= s.length() - 2)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        int j = s.indexOf(':');
        if(i < 0 && j < 0)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(j >= 0)
            if(i < 0 || i > j)
            {
                int k = 0;
                do
                {
                    if(k >= j)
                        break;
                    char c = s.charAt(k);
                    if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
                    {
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    k++;
                } while(true);
            } else
            {
                if(j >= s.length() - 2)
                {
                    flag = false;
                    continue; /* Loop/switch isn't completed */
                }
                int l = j + 1;
                do
                {
                    if(l >= j + 3)
                        break;
                    char c1 = s.charAt(l);
                    if(c1 < '0' || c1 > '9')
                    {
                        flag = false;
                        continue; /* Loop/switch isn't completed */
                    }
                    l++;
                } while(true);
            }
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static URIParsedResult parse(Result result)
    {
        String s = result.getText();
        if(s != null && s.startsWith("URL:"))
            s = s.substring(4);
        URIParsedResult uriparsedresult;
        if(!isBasicallyValidURI(s))
            uriparsedresult = null;
        else
            uriparsedresult = new URIParsedResult(s, null);
        return uriparsedresult;
    }
}
