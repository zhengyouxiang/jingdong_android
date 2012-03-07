// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ProductParsedResult

final class ProductResultParser extends ResultParser
{

    private ProductResultParser()
    {
    }

    public static ProductParsedResult parse(Result result)
    {
        BarcodeFormat barcodeformat = result.getBarcodeFormat();
        if(BarcodeFormat.UPC_A.equals(barcodeformat) || BarcodeFormat.UPC_E.equals(barcodeformat) || BarcodeFormat.EAN_8.equals(barcodeformat) || BarcodeFormat.EAN_13.equals(barcodeformat)) goto _L2; else goto _L1
_L1:
        ProductParsedResult productparsedresult = null;
_L4:
        return productparsedresult;
_L2:
        String s = result.getText();
        if(s == null)
        {
            productparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        int i = s.length();
        int j = 0;
        do
        {
            if(j >= i)
                break;
            char c = s.charAt(j);
            if(c < '0' || c > '9')
            {
                productparsedresult = null;
                continue; /* Loop/switch isn't completed */
            }
            j++;
        } while(true);
        String s1;
        if(BarcodeFormat.UPC_E.equals(barcodeformat))
            s1 = UPCEReader.convertUPCEtoUPCA(s);
        else
            s1 = s;
        productparsedresult = new ProductParsedResult(s, s1);
        if(true) goto _L4; else goto _L3
_L3:
    }
}
