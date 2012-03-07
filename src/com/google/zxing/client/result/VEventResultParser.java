// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, VCardResultParser, CalendarParsedResult

final class VEventResultParser extends ResultParser
{

    private VEventResultParser()
    {
    }

    public static CalendarParsedResult parse(Result result)
    {
        String s = result.getText();
        CalendarParsedResult calendarparsedresult;
        if(s == null)
            calendarparsedresult = null;
        else
        if(s.indexOf("BEGIN:VEVENT") < 0)
        {
            calendarparsedresult = null;
        } else
        {
            String s1 = VCardResultParser.matchSingleVCardPrefixedField("SUMMARY", s, true);
            String s2 = VCardResultParser.matchSingleVCardPrefixedField("DTSTART", s, true);
            String s3 = VCardResultParser.matchSingleVCardPrefixedField("DTEND", s, true);
            String s4 = VCardResultParser.matchSingleVCardPrefixedField("LOCATION", s, true);
            String s5 = VCardResultParser.matchSingleVCardPrefixedField("DESCRIPTION", s, true);
            try
            {
                calendarparsedresult = new CalendarParsedResult(s1, s2, s3, s4, null, s5);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                calendarparsedresult = null;
            }
        }
        return calendarparsedresult;
    }
}
