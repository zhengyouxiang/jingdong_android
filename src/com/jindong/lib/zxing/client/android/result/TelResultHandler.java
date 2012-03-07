// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.TelParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class TelResultHandler extends ResultHandler
{

    public TelResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    public int getButtonCount()
    {
        return buttons.length;
    }

    public int getButtonText(int i)
    {
        return buttons[i];
    }

    public CharSequence getDisplayContents()
    {
        return PhoneNumberUtils.formatNumber(getResult().getDisplayResult().replace("\r", ""));
    }

    public int getDisplayTitle()
    {
        return 0x7f09023d;
    }

    public void handleButtonPress(int i)
    {
        TelParsedResult telparsedresult = (TelParsedResult)getResult();
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 33
    //                   1 44;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        dialPhoneFromUri(telparsedresult.getTelURI());
        continue; /* Loop/switch isn't completed */
_L3:
        String as[] = new String[1];
        as[0] = telparsedresult.getNumber();
        addContact(null, as, null, null, null, null, null);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[2];
        ai[0] = 0x7f0901e8;
        ai[1] = 0x7f0901e2;
        buttons = ai;
    }
}
