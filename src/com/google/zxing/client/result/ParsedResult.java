// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResultType

public abstract class ParsedResult
{

    protected ParsedResult(ParsedResultType parsedresulttype)
    {
        type = parsedresulttype;
    }

    public static void maybeAppend(String s, StringBuffer stringbuffer)
    {
        if(s != null && s.length() > 0)
        {
            if(stringbuffer.length() > 0)
                stringbuffer.append('\n');
            stringbuffer.append(s);
        }
    }

    public static void maybeAppend(String as[], StringBuffer stringbuffer)
    {
        if(as != null)
        {
            for(int i = 0; i < as.length; i++)
            {
                if(as[i] == null || as[i].length() <= 0)
                    continue;
                if(stringbuffer.length() > 0)
                    stringbuffer.append('\n');
                stringbuffer.append(as[i]);
            }

        }
    }

    public abstract String getDisplayResult();

    public ParsedResultType getType()
    {
        return type;
    }

    public String toString()
    {
        return getDisplayResult();
    }

    private final ParsedResultType type;
}
