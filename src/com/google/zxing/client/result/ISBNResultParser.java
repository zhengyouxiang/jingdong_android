// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ISBNParsedResult

public class ISBNResultParser extends ResultParser
{

    private ISBNResultParser()
    {
    }

    public static ISBNParsedResult parse(Result result)
    {
        BarcodeFormat barcodeformat = result.getBarcodeFormat();
        ISBNParsedResult isbnparsedresult;
        if(!BarcodeFormat.EAN_13.equals(barcodeformat))
        {
            isbnparsedresult = null;
        } else
        {
            String s = result.getText();
            if(s == null)
                isbnparsedresult = null;
            else
            if(s.length() != 13)
                isbnparsedresult = null;
            else
            if(!s.startsWith("978") && !s.startsWith("979"))
                isbnparsedresult = null;
            else
                isbnparsedresult = new ISBNParsedResult(s);
        }
        return isbnparsedresult;
    }
}
