// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class TextParsedResult extends ParsedResult
{

    public TextParsedResult(String s, String s1)
    {
        super(ParsedResultType.TEXT);
        text = s;
        language = s1;
    }

    public String getDisplayResult()
    {
        return text;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getText()
    {
        return text;
    }

    private final String language;
    private final String text;
}
