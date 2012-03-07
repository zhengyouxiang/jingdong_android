// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, AddressBookParsedResult

final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser
{

    AddressBookDoCoMoResultParser()
    {
    }

    public static AddressBookParsedResult parse(Result result)
    {
        String s = result.getText();
        AddressBookParsedResult addressbookparsedresult;
        if(s == null || !s.startsWith("MECARD:"))
        {
            addressbookparsedresult = null;
        } else
        {
            String as[] = matchDoCoMoPrefixedField("N:", s, true);
            if(as == null)
            {
                addressbookparsedresult = null;
            } else
            {
                String s1 = parseName(as[0]);
                String s2 = matchSingleDoCoMoPrefixedField("SOUND:", s, true);
                String as1[] = matchDoCoMoPrefixedField("TEL:", s, true);
                String as2[] = matchDoCoMoPrefixedField("EMAIL:", s, true);
                String s3 = matchSingleDoCoMoPrefixedField("NOTE:", s, false);
                String as3[] = matchDoCoMoPrefixedField("ADR:", s, true);
                String s4 = matchSingleDoCoMoPrefixedField("BDAY:", s, true);
                String s5;
                String s6;
                String s7;
                if(s4 != null && !isStringOfDigits(s4, 8))
                    s5 = null;
                else
                    s5 = s4;
                s6 = matchSingleDoCoMoPrefixedField("URL:", s, true);
                s7 = matchSingleDoCoMoPrefixedField("ORG:", s, true);
                addressbookparsedresult = new AddressBookParsedResult(maybeWrap(s1), s2, as1, as2, s3, as3, s7, s5, null, s6);
            }
        }
        return addressbookparsedresult;
    }

    private static String parseName(String s)
    {
        int i = s.indexOf(',');
        String s1;
        if(i >= 0)
            s1 = s.substring(i + 1) + ' ' + s.substring(0, i);
        else
            s1 = s;
        return s1;
    }
}
