// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.WifiParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class WifiResultHandler extends ResultHandler
{

    public WifiResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
        parent = activity;
    }

    public int getButtonCount()
    {
        return 1;
    }

    public int getButtonText(int i)
    {
        if(i == 0)
            return 0x7f0901fc;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public CharSequence getDisplayContents()
    {
        WifiParsedResult wifiparsedresult = (WifiParsedResult)getResult();
        StringBuffer stringbuffer = new StringBuffer(50);
        ParsedResult.maybeAppend((new StringBuilder(String.valueOf(parent.getString(0x7f09024c)))).append('\n').append(wifiparsedresult.getSsid()).toString(), stringbuffer);
        ParsedResult.maybeAppend((new StringBuilder(String.valueOf(parent.getString(0x7f09024d)))).append('\n').append(wifiparsedresult.getNetworkEncryption()).toString(), stringbuffer);
        return stringbuffer.toString();
    }

    public int getDisplayTitle()
    {
        return 0x7f090240;
    }

    public void handleButtonPress(int i)
    {
        WifiParsedResult wifiparsedresult = (WifiParsedResult)getResult();
        if(i == 0)
            wifiConnect(wifiparsedresult);
    }

    private final Activity parent;
}
