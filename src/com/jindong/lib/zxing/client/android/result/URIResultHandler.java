// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.URIParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class URIResultHandler extends ResultHandler
{

    public URIResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    private boolean isGoogleBooksURI()
    {
        return ((URIParsedResult)getResult()).getURI().startsWith("http://google.com/books?id=");
    }

    public int getButtonCount()
    {
        int i;
        if(isGoogleBooksURI())
            i = buttons.length;
        else
            i = buttons.length - 1;
        return i;
    }

    public int getButtonText(int i)
    {
        return buttons[i];
    }

    public int getDisplayTitle()
    {
        return 0x7f09023f;
    }

    public void handleButtonPress(int i)
    {
        String s = ((URIParsedResult)getResult()).getURI();
        i;
        JVM INSTR tableswitch 0 3: default 44
    //                   0 45
    //                   1 53
    //                   2 61
    //                   3 69;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        openURL(s);
        continue; /* Loop/switch isn't completed */
_L3:
        shareByEmail(s);
        continue; /* Loop/switch isn't completed */
_L4:
        shareBySMS(s);
        continue; /* Loop/switch isn't completed */
_L5:
        searchBookContents(s);
        if(true) goto _L1; else goto _L6
_L6:
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[4];
        ai[0] = 0x7f0901ef;
        ai[1] = 0x7f0901f5;
        ai[2] = 0x7f0901f6;
        ai[3] = 0x7f0901f2;
        buttons = ai;
    }
}
