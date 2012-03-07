// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, SMSParsedResult

final class SMSTOMMSTOResultParser extends ResultParser
{

    private SMSTOMMSTOResultParser()
    {
    }

    public static SMSParsedResult parse(Result result)
    {
        String s = result.getText();
        SMSParsedResult smsparsedresult;
        if(s == null)
            smsparsedresult = null;
        else
        if(!s.startsWith("smsto:") && !s.startsWith("SMSTO:") && !s.startsWith("mmsto:") && !s.startsWith("MMSTO:"))
        {
            smsparsedresult = null;
        } else
        {
            String s1 = s.substring(6);
            int i = s1.indexOf(':');
            String s2;
            String s3;
            if(i >= 0)
            {
                String s4 = s1.substring(i + 1);
                s2 = s1.substring(0, i);
                s3 = s4;
            } else
            {
                s2 = s1;
                s3 = null;
            }
            smsparsedresult = new SMSParsedResult(s2, null, null, s3);
        }
        return smsparsedresult;
    }
}
