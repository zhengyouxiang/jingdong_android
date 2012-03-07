// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import com.jindong.app.mall.MyApplication;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public final class CommonUtil
{
    public static interface MacAddressListener
    {

        public abstract void setMacAddress(String s);
    }


    public CommonUtil()
    {
    }

    public static boolean CheckNetWork()
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager)MyApplication.getInstance().getSystemService("connectivity");
        if(connectivitymanager != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        NetworkInfo anetworkinfo[];
        int i;
        anetworkinfo = connectivitymanager.getAllNetworkInfo();
        if(anetworkinfo == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
_L5:
        if(i >= anetworkinfo.length)
        {
            flag = false;
        } else
        {
label0:
            {
                if(!anetworkinfo[i].isConnected())
                    break label0;
                flag = true;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public static void backToMain(Context context)
    {
        Intent intent;
        ActivityInfo activityinfo = context.getPackageManager().resolveActivity((new Intent("android.intent.action.MAIN")).addCategory("android.intent.category.HOME"), 0).activityInfo;
        intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(activityinfo.packageName, activityinfo.name));
        intent.addFlags(0x10000000);
        context.startActivity(intent);
_L2:
        return;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean checkAddrWithSpace(String s)
    {
        return startCheck("[\\w\u4E00-\u9FA5\\-\\x20]+", s);
    }

    public static boolean checkEmailWithSuffix(String s)
    {
        return startCheck("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", s);
    }

    public static int checkNetWorkType()
    {
        byte byte0;
        if(Proxy.getDefaultHost() != null)
            byte0 = 2;
        else
            byte0 = 1;
        return byte0;
    }

    public static boolean checkPassword(String s, int i, int j)
    {
        return startCheck((new StringBuilder("[a-zA-Z_0-9\\-]{")).append(i).append(",").append(j).append("}").toString(), s);
    }

    public static boolean checkSDcard()
    {
        boolean flag;
        if(Environment.getExternalStorageState().equals("mounted"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean checkUsername(String s)
    {
        return startCheck("[\\w\u4E00-\u9FA5\\-a-zA-Z0-9_]+", s);
    }

    public static boolean checkUsername(String s, int i)
    {
        return startCheck((new StringBuilder("[\\w\u4E00-\u9FA5\\-a-zA-Z0-9_]{")).append(i).append(",}").toString(), s);
    }

    public static boolean checkUsername(String s, int i, int j)
    {
        return startCheck((new StringBuilder("[\\w\u4E00-\u9FA5\\-a-zA-Z0-9_]{")).append(i).append(",").append(j).append("}").toString(), s);
    }

    public static String getDeviceId()
    {
        return ((TelephonyManager)MyApplication.getInstance().getSystemService("phone")).getDeviceId();
    }

    public static SharedPreferences getJdSharedPreferences()
    {
        return MyApplication.getInstance().getSharedPreferences("jdAndroidClient", 0);
    }

    public static int getLength(String s)
    {
        char ac[] = s.toCharArray();
        int i = 0;
        int j = 0;
        do
        {
            if(j >= ac.length)
                return i;
            if(isChinese(ac[j]))
                i += 2;
            else
                i++;
            j++;
        } while(true);
    }

    /**
     * @deprecated Method getLocalMacAddress is deprecated
     */

    public static void getLocalMacAddress(MacAddressListener macaddresslistener)
    {
        com/jindong/app/mall/utils/CommonUtil;
        JVM INSTR monitorenter ;
        final WifiManager wifi;
        String s;
        if(Log.D)
            Log.d("Temp", "getMacAddress() -->> ");
        wifi = (WifiManager)MyApplication.getInstance().getSystemService("wifi");
        s = wifi.getConnectionInfo().getMacAddress();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getMacAddress() macAddress without open -->> ")).append(s).toString());
        if(s == null) goto _L2; else goto _L1
_L1:
        macaddresslistener.setMacAddress(s);
_L4:
        com/jindong/app/mall/utils/CommonUtil;
        JVM INSTR monitorexit ;
        return;
_L2:
        (new Thread() {

            public void run()
            {
                int i;
                if(Log.D)
                    Log.d("Temp", "run() -->> ");
                wifi.setWifiEnabled(true);
                if(Log.D)
                    Log.d("Temp", "run() setWifiEnabled -->> true");
                i = 0;
_L1:
                String s1 = wifi.getConnectionInfo().getMacAddress();
                if(s1 != null || i >= 60)
                {
                    wifi.setWifiEnabled(false);
                    if(Log.D)
                        Log.d("Temp", "run() setWifiEnabled -->> false");
                    if(Log.D)
                        Log.d("Temp", (new StringBuilder("getMacAddress() macAddress with open -->> ")).append(s1).toString());
                    listener.setMacAddress(s1);
                    return;
                }
                i++;
                Object obj = waiter;
                obj;
                JVM INSTR monitorenter ;
                if(Log.D)
                    Log.d("Temp", "getMacAddress() wait start 500 -->> ");
                waiter.wait(500L);
                if(Log.D)
                    Log.d("Temp", "getMacAddress() wait end 500 -->> ");
_L2:
                obj;
                JVM INSTR monitorexit ;
                  goto _L1
                Exception exception1;
                exception1;
                throw exception1;
                InterruptedException interruptedexception;
                interruptedexception;
                interruptedexception.printStackTrace();
                  goto _L2
            }

            private final MacAddressListener val$listener;
            private final Object val$waiter;
            private final WifiManager val$wifi;

            
            {
                wifi = wifimanager;
                waiter = obj;
                listener = macaddresslistener;
                super();
            }
        }
).start();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean isChinese(char c)
    {
        Character.UnicodeBlock unicodeblock = Character.UnicodeBlock.of(c);
        boolean flag;
        if(unicodeblock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeblock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeblock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeblock == Character.UnicodeBlock.GENERAL_PUNCTUATION || unicodeblock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || unicodeblock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean startCheck(String s, String s1)
    {
        return Pattern.compile(s).matcher(s1).matches();
    }

    public String getLocalIpAddress()
    {
        Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
_L2:
        Enumeration enumeration1;
        if(!enumeration.hasMoreElements())
            break MISSING_BLOCK_LABEL_93;
        enumeration1 = ((NetworkInterface)enumeration.nextElement()).getInetAddresses();
_L4:
        if(!enumeration1.hasMoreElements()) goto _L2; else goto _L1
_L1:
        InetAddress inetaddress = (InetAddress)enumeration1.nextElement();
        if(inetaddress.isLoopbackAddress()) goto _L4; else goto _L3
_L3:
        String s1 = inetaddress.getHostAddress().toString();
        String s;
        s = s1;
        break MISSING_BLOCK_LABEL_95;
        SocketException socketexception;
        socketexception;
        if(Log.V)
            Log.v("WifiPreference IpAddress", socketexception.toString());
        s = null;
        return s;
    }
}
