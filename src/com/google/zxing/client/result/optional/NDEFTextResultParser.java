// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.TextParsedResult;

// Referenced classes of package com.google.zxing.client.result.optional:
//            AbstractNDEFResultParser, NDEFRecord

final class NDEFTextResultParser extends AbstractNDEFResultParser
{

    NDEFTextResultParser()
    {
    }

    static String[] decodeTextPayload(byte abyte0[])
    {
        byte byte0 = abyte0[0];
        boolean flag;
        int i;
        String s;
        String s1;
        String s2;
        String as[];
        if((byte0 & 0x80) != 0)
            flag = true;
        else
            flag = false;
        i = byte0 & 0x1f;
        s = bytesToString(abyte0, 1, i, "US-ASCII");
        if(flag)
            s1 = "UTF-16";
        else
            s1 = "UTF8";
        s2 = bytesToString(abyte0, i + 1, abyte0.length - i - 1, s1);
        as = new String[2];
        as[0] = s;
        as[1] = s2;
        return as;
    }

    public static TextParsedResult parse(Result result)
    {
        byte abyte0[] = result.getRawBytes();
        TextParsedResult textparsedresult;
        if(abyte0 == null)
        {
            textparsedresult = null;
        } else
        {
            NDEFRecord ndefrecord = NDEFRecord.readRecord(abyte0, 0);
            if(ndefrecord == null || !ndefrecord.isMessageBegin() || !ndefrecord.isMessageEnd())
                textparsedresult = null;
            else
            if(!ndefrecord.getType().equals("T"))
            {
                textparsedresult = null;
            } else
            {
                String as[] = decodeTextPayload(ndefrecord.getPayload());
                textparsedresult = new TextParsedResult(as[0], as[1]);
            }
        }
        return textparsedresult;
    }
}
