// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.cpa;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.utils.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.cpa:
//            RSAHelper

public class SysInfoManager
{
    static class BTInfo
    {

        private String getBTmac()
        {
            BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
            String s;
            if(bluetoothadapter != null && bluetoothadapter.isEnabled())
                s = (new StringBuilder(String.valueOf('"'))).append(bluetoothadapter.getAddress()).append('"').toString();
            else
                s = "\"Unknow\"";
            return s;
        }


        BTInfo()
        {
        }
    }


    public SysInfoManager()
    {
    }

    public static byte[] base64Dencode(byte abyte0[])
    {
        byte abyte1[];
        Log.i(TAG, (new StringBuilder("ckey")).append(new String(abyte0)).toString());
        abyte1 = (byte[])null;
        byte abyte2[];
        RSAPublicKey rsapublickey = RSAHelper.getPublicKey(new String(abyte0));
        Log.i(TAG, rsapublickey.getModulus().toString());
        Log.i(TAG, rsapublickey.getPublicExponent().toString());
        abyte2 = rsapublickey.getModulus().toString(10).getBytes();
        abyte1 = abyte2;
_L2:
        Log.i(TAG, new String(abyte1));
        return abyte1;
        Exception exception;
        exception;
        exception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static byte[] base64Encode(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer(Base64.encodeBytes(abyte0));
        stringbuffer.append('|');
        Log.i(TAG, stringbuffer.toString());
        return stringbuffer.toString().getBytes();
    }

    public static boolean externalMemoryAvailable()
    {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long extractMemCount(String s)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        int i = s.indexOf(':');
        if(i == -1) goto _L4; else goto _L3
_L3:
        String s1;
        int j;
        s1 = s.substring(i + 1).trim();
        j = s1.lastIndexOf(' ');
        if(j == -1) goto _L6; else goto _L5
_L5:
        String s2 = s1.substring(j + 1);
        long l1;
        l1 = Long.parseLong(s1.substring(0, j).trim());
        if("kb".equalsIgnoreCase(s2))
            l1 *= 1024L;
        else
        if("mb".equalsIgnoreCase(s2))
            l1 *= 0x100000L;
        else
        if("gb".equalsIgnoreCase(s2))
            l1 *= 0x40000000L;
        else
            Log.w(TAG, (new StringBuilder("Unexpected mem unit format: ")).append(s1).toString());
          goto _L7
        Exception exception;
        exception;
        Log.e(TAG, exception.getLocalizedMessage(), exception);
_L2:
        long l;
        l = -1L;
        break; /* Loop/switch isn't completed */
_L6:
        Log.e(TAG, (new StringBuilder("Unexpected mem value format: ")).append(s1).toString());
        continue; /* Loop/switch isn't completed */
_L4:
        Log.e(TAG, (new StringBuilder("Unexpected mem format: ")).append(s).toString());
        if(true) goto _L2; else goto _L8
_L7:
        l = l1;
_L8:
        return l;
    }

    public static String getBTMacAdrress()
    {
        String s;
        if(android.os.Build.VERSION.SDK_INT > 5)
        {
            Log.i("test", (new StringBuilder()).append(android.os.Build.VERSION.SDK_INT).toString());
            s = (new BTInfo()).getBTmac();
        } else
        {
            s = "\"Unknow\"";
        }
        return s;
    }

    public static String getBuildInfo()
    {
        StringBuffer stringbuffer = new StringBuffer("\"buildInfo\":");
        stringbuffer.append('"');
        stringbuffer.append('{');
        stringbuffer.append("device:").append(Build.DEVICE).append(',');
        stringbuffer.append("model:").append(Build.MODEL).append(',');
        stringbuffer.append("product:").append(Build.PRODUCT).append(',');
        stringbuffer.append("brand:").append(Build.BRAND).append(',');
        stringbuffer.append("release:").append(android.os.Build.VERSION.RELEASE).append(',');
        stringbuffer.append("display:").append(Build.DISPLAY).append(',');
        stringbuffer.append("locale:").append(Locale.getDefault().toString());
        stringbuffer.append('}');
        stringbuffer.append('"');
        return stringbuffer.toString();
    }

    public static String getCPUSerial()
    {
        String s = "0000000000000000";
        LineNumberReader linenumberreader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
        int i = 1;
_L4:
        if(i < 100) goto _L2; else goto _L1
_L1:
        return (new StringBuilder(String.valueOf('"'))).append(s).append('"').toString();
_L2:
        String s1 = linenumberreader.readLine();
        if(s1 == null) goto _L1; else goto _L3
_L3:
        String s2;
        if(s1.indexOf("Serial") <= -1)
            break MISSING_BLOCK_LABEL_118;
        s2 = s1.substring(1 + s1.indexOf(":"), s1.length()).trim();
        s = s2;
          goto _L1
        i++;
          goto _L4
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L1
    }

    public static String getDeviceId()
    {
        return (new StringBuilder(String.valueOf('"'))).append(CommonUtil.getDeviceId()).append('"').toString();
    }

    public static String getIMSIstr()
    {
        String s = ((TelephonyManager)MyApplication.getInstance().getSystemService("phone")).getSubscriberId();
        return (new StringBuilder(String.valueOf('"'))).append(s).append('"').toString();
    }

    public static String getMemInfo()
    {
        StringBuffer stringbuffer = new StringBuffer("\"memSize\":");
        long al[] = getMemState(MyApplication.getInstance());
        stringbuffer.append('"');
        if(al == null)
            stringbuffer.append("Unknow");
        else
            stringbuffer.append(Formatter.formatFileSize(MyApplication.getInstance(), al[0]));
        stringbuffer.append('"');
        stringbuffer.append(",");
        return stringbuffer.toString();
    }

    static long[] getMemState(Context context)
    {
        BufferedReader bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))), 1024);
        String s;
        String s1;
        s = null;
        s1 = null;
_L7:
        String s2 = bufferedreader1.readLine();
        if(s2 != null) goto _L2; else goto _L1
_L1:
        long al1[];
        al1 = new long[3];
        al1[0] = extractMemCount(s);
        al1[1] = extractMemCount(s1);
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        android.app.ActivityManager.MemoryInfo memoryinfo = new android.app.ActivityManager.MemoryInfo();
        activitymanager.getMemoryInfo(memoryinfo);
        al1[2] = memoryinfo.availMem;
        long al[];
        boolean flag;
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            catch(IOException ioexception2)
            {
                Log.e(TAG, ioexception2.getLocalizedMessage(), ioexception2);
            }
        al = al1;
_L3:
        return al;
_L2:
        if(s2.startsWith("MemTotal"))
        {
            s = s2;
            continue; /* Loop/switch isn't completed */
        }
        flag = s2.startsWith("MemFree");
        if(flag)
            s1 = s2;
        continue; /* Loop/switch isn't completed */
        Exception exception3;
        exception3;
        Exception exception1 = exception3;
_L6:
        Log.e(TAG, exception1.getLocalizedMessage(), exception1);
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception1)
            {
                Log.e(TAG, ioexception1.getLocalizedMessage(), ioexception1);
            }
        al = null;
          goto _L3
        Exception exception2;
        exception2;
_L5:
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception)
            {
                Log.e(TAG, ioexception.getLocalizedMessage(), ioexception);
            }
        throw exception2;
        exception2;
        bufferedreader = bufferedreader1;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        exception1 = exception;
        bufferedreader = bufferedreader1;
          goto _L6
        if(s == null || s1 == null) goto _L7; else goto _L1
    }

    public static String getNetAddressInfo()
    {
        StringBuffer stringbuffer;
        Enumeration enumeration;
        stringbuffer = new StringBuffer();
        enumeration = NetworkInterface.getNetworkInterfaces();
_L8:
        if(enumeration.hasMoreElements()) goto _L2; else goto _L1
_L1:
        String s;
        String s2 = stringbuffer.toString();
        if(!TextUtils.isEmpty(s2))
        {
            s = s2;
            break MISSING_BLOCK_LABEL_154;
        }
          goto _L3
_L2:
        Enumeration enumeration1 = ((NetworkInterface)enumeration.nextElement()).getInetAddresses();
_L6:
        String s1;
        if(!enumeration1.hasMoreElements())
            break; /* Loop/switch isn't completed */
        InetAddress inetaddress = (InetAddress)enumeration1.nextElement();
        if(inetaddress.isLoopbackAddress())
            continue; /* Loop/switch isn't completed */
        s1 = inetaddress.getHostAddress();
        if(TextUtils.isEmpty(s1))
            continue; /* Loop/switch isn't completed */
        if(stringbuffer.length() == 0)
        {
            stringbuffer.append(s1);
            continue; /* Loop/switch isn't completed */
        }
          goto _L4
        SocketException socketexception;
        socketexception;
        Log.e(TAG, socketexception.getLocalizedMessage(), socketexception);
_L3:
        s = null;
        break MISSING_BLOCK_LABEL_154;
_L4:
        stringbuffer.append(", ").append(s1);
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L8; else goto _L7
_L7:
        return s;
    }

    public static String getNetworkInfoStr()
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager)MyApplication.getInstance().getSystemService("connectivity");
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("\"netwrokInfo\":");
        stringbuilder.append('"');
        stringbuilder.append("{");
        NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
        if(networkinfo != null && networkinfo.isConnected() && networkinfo.getType() == 1)
        {
            WifiManager wifimanager = (WifiManager)MyApplication.getInstance().getSystemService("wifi");
            WifiInfo wifiinfo = wifimanager.getConnectionInfo();
            if(wifiinfo != null)
            {
                stringbuilder.append("SSID: ").append(wifiinfo.getSSID()).append(',');
                stringbuilder.append("BSSID: ").append(wifiinfo.getBSSID()).append(',');
                stringbuilder.append("MAC: ").append(wifiinfo.getMacAddress()).append(',');
            }
            DhcpInfo dhcpinfo = wifimanager.getDhcpInfo();
            if(dhcpinfo != null)
            {
                stringbuilder.append("gateway:");
                putAddress(stringbuilder, dhcpinfo.gateway);
                stringbuilder.append(",");
                stringbuilder.append("ip:");
                putAddress(stringbuilder, dhcpinfo.ipAddress);
            }
        } else
        {
            String s = getNetAddressInfo();
            stringbuilder.append("ip:").append(s);
        }
        stringbuilder.append("}");
        stringbuilder.append('"');
        stringbuilder.append(",");
        return stringbuilder.toString();
    }

    public static String getSdCardId()
    {
        if(!externalMemoryAvailable()) goto _L2; else goto _L1
_L1:
        File file = new File("/sys/class/mmc_host/mmc1");
        if(!file.exists()) goto _L4; else goto _L3
_L3:
        String s1;
        File afile[];
        int i;
        s1 = null;
        afile = file.listFiles();
        i = 0;
_L10:
        if(i < afile.length) goto _L6; else goto _L5
_L5:
        String s;
        String s3 = (new BufferedReader(new FileReader((new StringBuilder(String.valueOf(s1))).append("/cid").toString()))).readLine();
        Log.d(TAG, (new StringBuilder("CID of the MMC = ")).append(s3).toString());
        s = (new StringBuilder(String.valueOf('"'))).append(s3).append('"').toString();
          goto _L7
_L6:
        if(!afile[i].toString().contains("mmc1:"))
            break; /* Loop/switch isn't completed */
        s1 = afile[i].toString();
        String s2 = (String)afile[i].toString().subSequence(s1.length() - 4, s1.length());
        Log.d(TAG, (new StringBuilder(" SID of MMC = ")).append(s2).toString());
        if(true) goto _L5; else goto _L8
        Exception exception;
        exception;
        Log.e("CID_APP", "Can not read SD-card cid");
_L4:
        s = "\"Unknow\"";
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        Log.e("CID_APP", " SD-card unavailble");
        if(true) goto _L4; else goto _L7
_L7:
        return s;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static String getSensorInfoString()
    {
        StringBuffer stringbuffer;
        List list;
        SensorManager sensormanager = (SensorManager)MyApplication.getInstance().getSystemService("sensor");
        stringbuffer = new StringBuffer("\"sensors\":");
        stringbuffer.append('"');
        if(sensormanager == null)
            break MISSING_BLOCK_LABEL_247;
        list = sensormanager.getSensorList(-1);
        if(list == null) goto _L2; else goto _L1
_L1:
        Iterator iterator;
        stringbuffer.append("[");
        iterator = list.iterator();
_L5:
        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
        if(list.size() > 0)
            stringbuffer.deleteCharAt(stringbuffer.length() - 1);
        stringbuffer.append("]");
_L2:
        stringbuffer.append('"');
        return stringbuffer.toString();
_L4:
        Sensor sensor = (Sensor)iterator.next();
        stringbuffer.append("{");
        StringBuffer stringbuffer1 = stringbuffer.append("sensorName:");
        String s;
        StringBuffer stringbuffer2;
        String s1;
        if(TextUtils.isEmpty(sensor.getName()))
            s = "Unknow";
        else
            s = sensor.getName();
        stringbuffer1.append(s);
        stringbuffer2 = stringbuffer.append(",vendor:");
        if(TextUtils.isEmpty(sensor.getVendor()))
            s1 = "Unknow";
        else
            s1 = sensor.getVendor();
        stringbuffer2.append(s1);
        stringbuffer.append(",resolution:").append(sensor.getResolution());
        stringbuffer.append("},");
          goto _L5
        stringbuffer.append("Unknow");
          goto _L2
    }

    public static String getWifiMac()
    {
        String s;
        String s1;
        s = StatisticsReportUtil.readDeviceUUID();
        s1 = null;
        if(s != null) goto _L2; else goto _L1
_L1:
        s1 = ((WifiManager)MyApplication.getInstance().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if(s1 == null)
            s1 = "Unknow";
_L4:
        return (new StringBuilder(String.valueOf('"'))).append(s1).append('"').toString();
_L2:
        String as[] = s.split("-");
        if(as != null)
            s1 = as[as.length - 1];
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void putAddress(StringBuilder stringbuilder, int i)
    {
        StringBuilder stringbuilder1 = stringbuilder.append(i & 0xff).append('.');
        int j = i >>> 8;
        StringBuilder stringbuilder2 = stringbuilder1.append(j & 0xff).append('.');
        int k = j >>> 8;
        stringbuilder2.append(k & 0xff).append('.').append(0xff & k >>> 8);
    }

    private static final String F_CPU_INFO = "/proc/cpuinfo";
    private static final String F_MEM_INFO = "/proc/meminfo";
    private static final char QUOAR = 34;
    public static final String TAG = com/jindong/app/mall/cpa/SysInfoManager.getSimpleName();
    private static final String UNKNOW = "Unknow";

}
