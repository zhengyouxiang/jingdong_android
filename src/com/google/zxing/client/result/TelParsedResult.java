// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class TelParsedResult extends ParsedResult
{

    public TelParsedResult(String s, String s1, String s2)
    {
        super(ParsedResultType.TEL);
        number = s;
        telURI = s1;
        title = s2;
    }

    public String getDisplayResult()
    {
        StringBuffer stringbuffer = new StringBuffer(20);
        maybeAppend(number, stringbuffer);
        maybeAppend(title, stringbuffer);
        return stringbuffer.toString();
    }

    public String getNumber()
    {
        return number;
    }

    public String getTelURI()
    {
        return telURI;
    }

    public String getTitle()
    {
        return title;
    }

    private final String number;
    private final String telURI;
    private final String title;
}
