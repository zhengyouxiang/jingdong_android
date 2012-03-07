// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.config.Configuration;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, CommonUtil

public class StatisticsReportUtil
{

    public StatisticsReportUtil()
    {
    }

    public static String getDeviceInfoStr()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("uuid", readDeviceUUID());
            jsonobject.put("platform", 100);
            jsonobject.put("brand", spilitSubString(Build.MANUFACTURER, 12));
            jsonobject.put("model", spilitSubString(Build.MODEL, 12));
            Display display = ((WindowManager)MyApplication.getInstance().getSystemService("window")).getDefaultDisplay();
            jsonobject.put("screen", (new StringBuilder(String.valueOf(display.getHeight()))).append("*").append(display.getWidth()).toString());
            jsonobject.put("clientVersion", getSoftwareVersionName());
            jsonobject.put("osVersion", android.os.Build.VERSION.RELEASE);
            jsonobject.put("partner", Configuration.getProperty("partner", ""));
            jsonobject.put("nettype", getNetworkTypeName(MyApplication.getInstance()));
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getDeviceInfoStr() return -->> ")).append(jsonobject.toString()).toString());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return jsonobject.toString();
    }

    public static String getNetworkTypeName(Context context)
    {
        TelephonyManager telephonymanager;
        String s;
        NetworkInfo anetworkinfo[];
        int i;
        ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
        telephonymanager = (TelephonyManager)context.getSystemService("phone");
        s = null;
        anetworkinfo = connectivitymanager.getAllNetworkInfo();
        i = 0;
_L5:
        int j = anetworkinfo.length;
        if(i < j) goto _L2; else goto _L1
_L1:
        if(s == null)
            s = "UNKNOWN";
        return s;
_L2:
        try
        {
            if(anetworkinfo[i].isConnected())
                if(anetworkinfo[i].getTypeName().toUpperCase().contains("MOBILE"))
                    s = (new StringBuilder(String.valueOf(telephonymanager.getNetworkType()))).toString();
                else
                if(anetworkinfo[i].getTypeName().toUpperCase().contains("WIFI"))
                    s = "WIFI";
                else
                    s = "UNKNOWN";
            break; /* Loop/switch isn't completed */
        }
        catch(Exception exception)
        {
            s = "UNKNOWN";
        }
        if(true) goto _L1; else goto _L3
_L3:
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static PackageInfo getPackageInfo()
    {
        PackageInfo packageinfo1;
        MyApplication myapplication = MyApplication.getInstance();
        packageinfo1 = myapplication.getPackageManager().getPackageInfo(myapplication.getPackageName(), 0);
        PackageInfo packageinfo = packageinfo1;
_L2:
        return packageinfo;
        Exception exception;
        exception;
        packageinfo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static String getParamStr()
    {
        String s;
        if(!TextUtils.isEmpty(paramStr))
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getParamStr() -->> ")).append(paramStr).toString());
            s = paramStr;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("&uuid=").append(readDeviceUUID());
            stringbuffer.append(getParamStrWithOutDeviceUUID());
            paramStr = stringbuffer.toString();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getParamStr() create -->> ")).append(paramStr).toString());
            s = paramStr;
        }
        return s;
    }

    private static String getParamStrWithOutDeviceUUID()
    {
        String s;
        if(!TextUtils.isEmpty(paramStrWithOutDeviceUUID))
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getParamStrWithOutDeviceUUID() -->> ")).append(paramStrWithOutDeviceUUID).toString());
            s = paramStrWithOutDeviceUUID;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("&clientVersion=").append(spilitSubString(getSoftwareVersionName(), 12));
            stringbuffer.append("&client=").append("android");
            stringbuffer.append("&osVersion=").append(spilitSubString(android.os.Build.VERSION.RELEASE, 12));
            Display display = ((WindowManager)MyApplication.getInstance().getSystemService("window")).getDefaultDisplay();
            stringbuffer.append("&screen=").append((new StringBuilder(String.valueOf(display.getHeight()))).append("*").append(display.getWidth()).toString());
            paramStrWithOutDeviceUUID = stringbuffer.toString();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getParamStrWithOutDeviceUUID() create -->> ")).append(paramStrWithOutDeviceUUID).toString());
            s = paramStrWithOutDeviceUUID;
        }
        return s;
    }

    public static String getReportString(boolean flag)
    {
        String s;
        if(flag || getValidDeviceUUIDByInstant() != null)
            s = getParamStr();
        else
            s = getParamStrWithOutDeviceUUID();
        return s;
    }

    public static int getSoftwareVersionCode()
    {
        PackageInfo packageinfo = getPackageInfo();
        int i;
        if(packageinfo == null)
            i = 0;
        else
            i = packageinfo.versionCode;
        return i;
    }

    public static String getSoftwareVersionName()
    {
        PackageInfo packageinfo = getPackageInfo();
        String s;
        if(packageinfo == null)
            s = "";
        else
            s = packageinfo.versionName;
        return s;
    }

    private static String getValidDeviceUUIDByInstant()
    {
        String s1;
        if(!TextUtils.isEmpty(deivceUUID))
        {
            s1 = deivceUUID;
        } else
        {
            String s = CommonUtil.getJdSharedPreferences().getString("uuid", null);
            if(isValidDeviceUUID(s))
            {
                deivceUUID = s;
                s1 = deivceUUID;
            } else
            {
                s1 = null;
            }
        }
        return s1;
    }

    private static boolean isValidDeviceUUID(String s)
    {
        boolean flag;
        if(TextUtils.isEmpty(s))
        {
            flag = false;
        } else
        {
            String as[] = s.split("-");
            if(as.length > 1)
            {
                if(TextUtils.isEmpty(as[1]))
                    flag = false;
                else
                    flag = true;
            } else
            {
                flag = false;
            }
        }
        return flag;
    }

    public static String readDeviceUUID()
    {
        String s = getValidDeviceUUIDByInstant();
        if(s == null) goto _L2; else goto _L1
_L1:
        String s4;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("readDeviceUUID() read deivceUUID -->> ")).append(s).toString());
        s4 = s;
_L4:
        return s4;
_L2:
        if(Log.D)
            Log.d("Temp", "readDeviceUUID() create -->> ");
        StringBuilder stringbuilder = new StringBuilder();
        String s1 = CommonUtil.getDeviceId();
        if(!TextUtils.isEmpty(s1))
            s1 = s1.trim().replaceAll("-", "");
        String s2 = macAddress;
        if(s2 == null)
        {
            CommonUtil.getLocalMacAddress(macAddressListener);
            String s3;
            synchronized(macAddressListener)
            {
                try
                {
                    if(!already)
                    {
                        if(Log.D)
                            Log.d("Temp", "mac wait start -->> ");
                        macAddressListener.wait();
                        if(Log.D)
                            Log.d("Temp", "mac wait end -->> ");
                    }
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            }
            if(macAddress == null)
                s2 = "";
            else
                s2 = macAddress;
        }
        if(!TextUtils.isEmpty(s2))
            s2 = s2.trim().replaceAll("-|\\.|:", "");
        if(!TextUtils.isEmpty(s1))
            stringbuilder.append(s1);
        stringbuilder.append("-");
        if(!TextUtils.isEmpty(s2))
            stringbuilder.append(s2);
        s3 = stringbuilder.toString();
        if(isValidDeviceUUID(s3))
        {
            if(Log.D)
                Log.d("Temp", "readDeviceUUID() write -->> ");
            CommonUtil.getJdSharedPreferences().edit().putString("uuid", s3).commit();
        }
        if(Log.D)
            Log.d("Temp", (new StringBuilder("readDeviceUUID() create deivceUUID -->> ")).append(s3).toString());
        s4 = s3;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String spilitSubString(String s, int i)
    {
        if(s != null && s.length() > i)
            s = s.substring(0, i);
        return s;
    }

    private static final String DEVICE_INFO_STR = "deviceInfoStr";
    private static final String DEVICE_INFO_UUID = "uuid";
    private static boolean already;
    private static String deivceUUID;
    private static String macAddress;
    private static CommonUtil.MacAddressListener macAddressListener = new CommonUtil.MacAddressListener() {

        public void setMacAddress(String s)
        {
            this;
            JVM INSTR monitorenter ;
            StatisticsReportUtil.macAddress = s;
            StatisticsReportUtil.already = true;
            notifyAll();
            return;
        }

    }
;
    private static String paramStr;
    private static String paramStrWithOutDeviceUUID;



}
