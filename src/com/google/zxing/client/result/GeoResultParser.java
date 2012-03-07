// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, GeoParsedResult

final class GeoResultParser extends ResultParser
{

    private GeoResultParser()
    {
    }

    public static GeoParsedResult parse(Result result)
    {
        String s = result.getText();
        if(s != null && (s.startsWith("geo:") || s.startsWith("GEO:"))) goto _L2; else goto _L1
_L1:
        GeoParsedResult geoparsedresult = null;
_L11:
        return geoparsedresult;
_L2:
        String s2;
        String s3;
        int j;
        int k;
        int i = s.indexOf('?', 4);
        if(i < 0)
        {
            s2 = s.substring(4);
            s3 = null;
        } else
        {
            String s1 = s.substring(i + 1);
            s2 = s.substring(4, i);
            s3 = s1;
        }
        j = s2.indexOf(',');
        if(j < 0)
        {
            geoparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        k = s2.indexOf(',', j + 1);
        double d = Double.parseDouble(s2.substring(0, j));
        if(d <= 90D && d >= -90D) goto _L4; else goto _L3
_L4:
        if(k >= 0) goto _L6; else goto _L5
_L5:
        double d3;
        double d4;
        d3 = Double.parseDouble(s2.substring(j + 1));
        d4 = 0.0D;
          goto _L7
_L6:
        double d1;
        double d2;
        d1 = Double.parseDouble(s2.substring(j + 1, k));
        d2 = Double.parseDouble(s2.substring(k + 1));
        d3 = d1;
        d4 = d2;
          goto _L7
        NumberFormatException numberformatexception;
        numberformatexception;
        geoparsedresult = null;
        continue; /* Loop/switch isn't completed */
_L9:
        geoparsedresult = new GeoParsedResult(d, d3, d4, s3);
        continue; /* Loop/switch isn't completed */
_L3:
        geoparsedresult = null;
        continue; /* Loop/switch isn't completed */
_L7:
        if(d3 <= 180D && d3 >= -180D && d4 >= 0.0D) goto _L9; else goto _L8
_L8:
        geoparsedresult = null;
        if(true) goto _L11; else goto _L10
_L10:
    }
}
