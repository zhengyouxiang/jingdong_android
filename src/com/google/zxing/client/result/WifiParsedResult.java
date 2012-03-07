// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class WifiParsedResult extends ParsedResult
{

    public WifiParsedResult(String s, String s1, String s2)
    {
        super(ParsedResultType.WIFI);
        ssid = s1;
        networkEncryption = s;
        password = s2;
    }

    public String getDisplayResult()
    {
        StringBuffer stringbuffer = new StringBuffer(80);
        maybeAppend(ssid, stringbuffer);
        maybeAppend(networkEncryption, stringbuffer);
        maybeAppend(password, stringbuffer);
        return stringbuffer.toString();
    }

    public String getNetworkEncryption()
    {
        return networkEncryption;
    }

    public String getPassword()
    {
        return password;
    }

    public String getSsid()
    {
        return ssid;
    }

    private final String networkEncryption;
    private final String password;
    private final String ssid;
}
