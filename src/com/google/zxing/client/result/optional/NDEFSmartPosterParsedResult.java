// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;

import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;

public final class NDEFSmartPosterParsedResult extends ParsedResult
{

    NDEFSmartPosterParsedResult(int i, String s, String s1)
    {
        super(ParsedResultType.NDEF_SMART_POSTER);
        action = i;
        uri = s;
        title = s1;
    }

    public int getAction()
    {
        return action;
    }

    public String getDisplayResult()
    {
        String s;
        if(title == null)
            s = uri;
        else
            s = title + '\n' + uri;
        return s;
    }

    public String getTitle()
    {
        return title;
    }

    public String getURI()
    {
        return uri;
    }

    public static final int ACTION_DO = 0;
    public static final int ACTION_OPEN = 2;
    public static final int ACTION_SAVE = 1;
    public static final int ACTION_UNSPECIFIED = -1;
    private final int action;
    private final String title;
    private final String uri;
}
