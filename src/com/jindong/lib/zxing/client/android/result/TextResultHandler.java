// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class TextResultHandler extends ResultHandler
{

    public TextResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    public int getButtonCount()
    {
        int i;
        if(hasCustomProductSearch())
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
        return 0x7f09023e;
    }

    public void handleButtonPress(int i)
    {
        String s = getResult().getDisplayResult();
        i;
        JVM INSTR tableswitch 0 3: default 40
    //                   0 41
    //                   1 49
    //                   2 57
    //                   3 65;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        webSearch(s);
        continue; /* Loop/switch isn't completed */
_L3:
        shareByEmail(s);
        continue; /* Loop/switch isn't completed */
_L4:
        shareBySMS(s);
        continue; /* Loop/switch isn't completed */
_L5:
        openURL(fillInCustomSearchURL(s));
        if(true) goto _L1; else goto _L6
_L6:
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[4];
        ai[0] = 0x7f0901fb;
        ai[1] = 0x7f0901f5;
        ai[2] = 0x7f0901f6;
        ai[3] = 0x7f0901e7;
        buttons = ai;
    }
}
