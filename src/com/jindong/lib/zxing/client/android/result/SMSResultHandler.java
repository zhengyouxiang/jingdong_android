// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class SMSResultHandler extends ResultHandler
{

    public SMSResultHandler(Activity activity, ParsedResult parsedresult)
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
        SMSParsedResult smsparsedresult = (SMSParsedResult)getResult();
        StringBuffer stringbuffer = new StringBuffer(50);
        String as[] = smsparsedresult.getNumbers();
        String as1[] = new String[as.length];
        int i = 0;
        do
        {
            if(i >= as.length)
            {
                ParsedResult.maybeAppend(as1, stringbuffer);
                ParsedResult.maybeAppend(smsparsedresult.getSubject(), stringbuffer);
                ParsedResult.maybeAppend(smsparsedresult.getBody(), stringbuffer);
                return stringbuffer.toString();
            }
            as1[i] = PhoneNumberUtils.formatNumber(as[i]);
            i++;
        } while(true);
    }

    public int getDisplayTitle()
    {
        return 0x7f09023c;
    }

    public void handleButtonPress(int i)
    {
        SMSParsedResult smsparsedresult = (SMSParsedResult)getResult();
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 33
    //                   1 50;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        sendSMS(smsparsedresult.getNumbers()[0], smsparsedresult.getBody());
        continue; /* Loop/switch isn't completed */
_L3:
        sendMMS(smsparsedresult.getNumbers()[0], smsparsedresult.getSubject(), smsparsedresult.getBody());
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[2];
        ai[0] = 0x7f0901fa;
        ai[1] = 0x7f0901ed;
        buttons = ai;
    }
}
