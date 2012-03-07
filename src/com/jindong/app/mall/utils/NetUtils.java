// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.net.*;
import android.telephony.TelephonyManager;
import com.jindong.app.mall.MyApplication;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public class NetUtils
{

    public NetUtils()
    {
    }

    public static String getProxyHost()
    {
        String s1;
        if(getType() != 2)
        {
            s1 = null;
        } else
        {
            String s = Proxy.getDefaultHost();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getProxyHost() -->> ")).append(s).toString());
            s1 = s;
        }
        return s1;
    }

    public static int getType()
    {
        int i = 0x7fffffff;
        NetworkInfo networkinfo = ((ConnectivityManager)MyApplication.getInstance().getSystemService("connectivity")).getActiveNetworkInfo();
        if(networkinfo == null || !networkinfo.isConnected()) goto _L2; else goto _L1
_L1:
        if(networkinfo.getType() != 0) goto _L4; else goto _L3
_L3:
        int j;
        if(Log.D)
            Log.d("Temp", "netInfo.getType() == ConnectivityManager.TYPE_MOBILE -->> ");
        j = ((TelephonyManager)MyApplication.getInstance().getSystemService("phone")).getNetworkType();
        i = j;
_L10:
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getType() result -->> ")).append(i).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getType() result toTypeName() -->> ")).append(toTypeName(i)).toString());
        return i;
_L4:
        if(networkinfo.getType() != 1) goto _L6; else goto _L5
_L5:
        if(Log.D)
            Log.d("Temp", "netInfo.getType() == ConnectivityManager.TYPE_WIFI -->> ");
          goto _L7
_L6:
        if(Log.D)
            Log.d("Temp", "netInfo.getType() == ConnectivityManager.UNKNOWN -->> ");
          goto _L8
_L2:
        if(Log.D)
            Log.d("Temp", "netInfo.getType() == ConnectivityManager.NO_NET -->> ");
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        continue; /* Loop/switch isn't completed */
_L7:
        i = 0x7ffffffd;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 0x7ffffffe;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static String toTypeName(int i)
    {
        i;
        JVM INSTR lookupswitch 12: default 108
    //                   1: 125
    //                   2: 131
    //                   3: 137
    //                   4: 161
    //                   5: 167
    //                   6: 173
    //                   7: 179
    //                   8: 143
    //                   9: 149
    //                   10: 155
    //                   2147483645: 113
    //                   2147483647: 119;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        String s = "UNKNOWN";
_L15:
        return s;
_L12:
        s = "WIFI";
        continue; /* Loop/switch isn't completed */
_L13:
        s = "NO_NET";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "GPRS";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "EDGE";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "UMTS";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "HSDPA";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "HSUPA";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "HSPA";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "CDMA";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "CDMA - EvDo rev. 0";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "CDMA - EvDo rev. A";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "CDMA - 1xRTT";
        if(true) goto _L15; else goto _L14
_L14:
    }

    private static final int NO_NET = 0x7fffffff;
    private static final int ROAMING = 0x7ffffffc;
    private static final int UNKNOWN = 0x7ffffffe;
    private static final int WIFI = 0x7ffffffd;
}
