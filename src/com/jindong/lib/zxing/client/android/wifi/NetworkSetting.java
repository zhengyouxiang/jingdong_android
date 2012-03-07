// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.wifi;


final class NetworkSetting
{

    NetworkSetting(String s, String s1, WifiActivity.NetworkType networktype)
    {
        ssid = s;
        password = s1;
        networkType = networktype;
    }

    WifiActivity.NetworkType getNetworkType()
    {
        return networkType;
    }

    String getPassword()
    {
        return password;
    }

    String getSsid()
    {
        return ssid;
    }

    private final WifiActivity.NetworkType networkType;
    private final String password;
    private final String ssid;
}
