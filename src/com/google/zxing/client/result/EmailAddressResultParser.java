// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, EmailAddressParsedResult, EmailDoCoMoResultParser

final class EmailAddressResultParser extends ResultParser
{

    EmailAddressResultParser()
    {
    }

    public static EmailAddressParsedResult parse(Result result)
    {
        String s = result.getText();
        EmailAddressParsedResult emailaddressparsedresult;
        if(s == null)
            emailaddressparsedresult = null;
        else
        if(s.startsWith("mailto:") || s.startsWith("MAILTO:"))
        {
            String s1 = s.substring(7);
            int i = s1.indexOf('?');
            if(i >= 0)
                s1 = s1.substring(0, i);
            Hashtable hashtable = parseNameValuePairs(s);
            String s2;
            String s3;
            String s4;
            if(hashtable != null)
            {
                String s5;
                if(s1.length() == 0)
                    s3 = (String)hashtable.get("to");
                else
                    s3 = s1;
                s5 = (String)hashtable.get("subject");
                s4 = (String)hashtable.get("body");
                s2 = s5;
            } else
            {
                s2 = null;
                s3 = s1;
                s4 = null;
            }
            emailaddressparsedresult = new EmailAddressParsedResult(s3, s2, s4, s);
        } else
        if(!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(s))
            emailaddressparsedresult = null;
        else
            emailaddressparsedresult = new EmailAddressParsedResult(s, null, null, "mailto:" + s);
        return emailaddressparsedresult;
    }
}
