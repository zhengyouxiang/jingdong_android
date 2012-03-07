// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result.optional:
//            AbstractNDEFResultParser, NDEFRecord, NDEFTextResultParser, NDEFURIResultParser, 
//            NDEFSmartPosterParsedResult

final class NDEFSmartPosterResultParser extends AbstractNDEFResultParser
{

    NDEFSmartPosterResultParser()
    {
    }

    public static NDEFSmartPosterParsedResult parse(Result result)
    {
        byte abyte0[] = result.getRawBytes();
        if(abyte0 != null) goto _L2; else goto _L1
_L1:
        NDEFSmartPosterParsedResult ndefsmartposterparsedresult = null;
_L4:
        return ndefsmartposterparsedresult;
_L2:
        NDEFRecord ndefrecord = NDEFRecord.readRecord(abyte0, 0);
        if(ndefrecord == null || !ndefrecord.isMessageBegin() || !ndefrecord.isMessageEnd())
        {
            ndefsmartposterparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        if(!ndefrecord.getType().equals("Sp"))
        {
            ndefsmartposterparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        byte abyte1[] = ndefrecord.getPayload();
        String s = null;
        byte byte0 = -1;
        NDEFRecord ndefrecord1 = null;
        int i = 0;
        int j = 0;
        String s1 = null;
        do
        {
            if(j >= abyte1.length)
                break;
            ndefrecord1 = NDEFRecord.readRecord(abyte1, j);
            if(ndefrecord1 == null)
                break;
            if(i == 0 && !ndefrecord1.isMessageBegin())
            {
                ndefsmartposterparsedresult = null;
                continue; /* Loop/switch isn't completed */
            }
            String s2 = ndefrecord1.getType();
            if("T".equals(s2))
                s = NDEFTextResultParser.decodeTextPayload(ndefrecord1.getPayload())[1];
            else
            if("U".equals(s2))
                s1 = NDEFURIResultParser.decodeURIPayload(ndefrecord1.getPayload());
            else
            if("act".equals(s2))
                byte0 = ndefrecord1.getPayload()[0];
            i++;
            j += ndefrecord1.getTotalRecordLength();
        } while(true);
        NDEFRecord ndefrecord2 = ndefrecord1;
        if(i == 0 || ndefrecord2 != null && !ndefrecord2.isMessageEnd())
            ndefsmartposterparsedresult = null;
        else
            ndefsmartposterparsedresult = new NDEFSmartPosterParsedResult(byte0, s1, s);
        if(true) goto _L4; else goto _L3
_L3:
    }
}
