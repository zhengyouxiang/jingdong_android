// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.wifi;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import com.jindong.app.mall.utils.Log;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.jindong.lib.zxing.client.android.wifi:
//            NetworkSetting, NetworkUtil, WifiReceiver

public final class WifiActivity extends Activity
{
    public static final class NetworkType extends Enum
    {

        public static NetworkType valueOf(String s)
        {
            return (NetworkType)Enum.valueOf(com/jindong/lib/zxing/client/android/wifi/WifiActivity$NetworkType, s);
        }

        public static NetworkType[] values()
        {
            NetworkType anetworktype[] = ENUM$VALUES;
            int i = anetworktype.length;
            NetworkType anetworktype1[] = new NetworkType[i];
            System.arraycopy(anetworktype, 0, anetworktype1, 0, i);
            return anetworktype1;
        }

        private static final NetworkType ENUM$VALUES[];
        public static final NetworkType NETWORK_INVALID;
        public static final NetworkType NETWORK_NOPASS;
        public static final NetworkType NETWORK_WEP;
        public static final NetworkType NETWORK_WPA;

        static 
        {
            NETWORK_WEP = new NetworkType("NETWORK_WEP", 0);
            NETWORK_WPA = new NetworkType("NETWORK_WPA", 1);
            NETWORK_NOPASS = new NetworkType("NETWORK_NOPASS", 2);
            NETWORK_INVALID = new NetworkType("NETWORK_INVALID", 3);
            NetworkType anetworktype[] = new NetworkType[4];
            anetworktype[0] = NETWORK_WEP;
            anetworktype[1] = NETWORK_WPA;
            anetworktype[2] = NETWORK_NOPASS;
            anetworktype[3] = NETWORK_INVALID;
            ENUM$VALUES = anetworktype;
        }

        private NetworkType(String s, int i)
        {
            super(s, i);
        }
    }


    public WifiActivity()
    {
    }

    private int changeNetwork(NetworkSetting networksetting)
    {
        int i;
        if(networksetting.getSsid() == null || networksetting.getSsid().length() == 0)
            i = doError(0x7f090249);
        else
        if(networksetting.getNetworkType() == NetworkType.NETWORK_INVALID)
            i = doError(0x7f09024a);
        else
        if(networksetting.getPassword() == null || networksetting.getPassword().length() == 0 || networksetting.getNetworkType() == null || networksetting.getNetworkType() == NetworkType.NETWORK_NOPASS)
            i = changeNetworkUnEncrypted(networksetting);
        else
        if(networksetting.getNetworkType() == NetworkType.NETWORK_WPA)
            i = changeNetworkWPA(networksetting);
        else
            i = changeNetworkWEP(networksetting);
        return i;
    }

    private WifiConfiguration changeNetworkCommon(NetworkSetting networksetting)
    {
        statusView.setText(0x7f090247);
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Adding new configuration: \nSSID: ")).append(networksetting.getSsid()).append("\nType: ").append(networksetting.getNetworkType()).toString());
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.allowedAuthAlgorithms.clear();
        wificonfiguration.allowedGroupCiphers.clear();
        wificonfiguration.allowedKeyManagement.clear();
        wificonfiguration.allowedPairwiseCiphers.clear();
        wificonfiguration.allowedProtocols.clear();
        wificonfiguration.SSID = NetworkUtil.convertToQuotedString(networksetting.getSsid());
        wificonfiguration.hiddenSSID = true;
        return wificonfiguration;
    }

    private int changeNetworkUnEncrypted(NetworkSetting networksetting)
    {
        if(Log.D)
            Log.d(TAG, "Empty password prompting a simple account setting");
        WifiConfiguration wificonfiguration = changeNetworkCommon(networksetting);
        wificonfiguration.wepKeys[0] = "";
        wificonfiguration.allowedKeyManagement.set(0);
        wificonfiguration.wepTxKeyIndex = 0;
        return requestNetworkChange(wificonfiguration);
    }

    private int changeNetworkWEP(NetworkSetting networksetting)
    {
        WifiConfiguration wificonfiguration = changeNetworkCommon(networksetting);
        String s = networksetting.getPassword();
        if(NetworkUtil.isHexWepKey(s))
            wificonfiguration.wepKeys[0] = s;
        else
            wificonfiguration.wepKeys[0] = NetworkUtil.convertToQuotedString(s);
        wificonfiguration.allowedAuthAlgorithms.set(1);
        wificonfiguration.allowedGroupCiphers.set(3);
        wificonfiguration.allowedGroupCiphers.set(2);
        wificonfiguration.allowedGroupCiphers.set(0);
        wificonfiguration.allowedGroupCiphers.set(1);
        wificonfiguration.allowedKeyManagement.set(0);
        wificonfiguration.wepTxKeyIndex = 0;
        return requestNetworkChange(wificonfiguration);
    }

    private int changeNetworkWPA(NetworkSetting networksetting)
    {
        WifiConfiguration wificonfiguration = changeNetworkCommon(networksetting);
        String s = networksetting.getPassword();
        if(HEX_DIGITS_64.matcher(s).matches())
        {
            if(Log.D)
                Log.d(TAG, "A 64 bit hex password entered.");
            wificonfiguration.preSharedKey = s;
        } else
        {
            if(Log.D)
                Log.d(TAG, "A normal password entered: I am quoting it.");
            wificonfiguration.preSharedKey = NetworkUtil.convertToQuotedString(s);
        }
        wificonfiguration.allowedAuthAlgorithms.set(0);
        wificonfiguration.allowedProtocols.set(0);
        wificonfiguration.allowedKeyManagement.set(1);
        wificonfiguration.allowedGroupCiphers.set(2);
        wificonfiguration.allowedGroupCiphers.set(3);
        wificonfiguration.allowedProtocols.set(1);
        return requestNetworkChange(wificonfiguration);
    }

    private int doError(int i)
    {
        statusView.setText(i);
        wifiManager.disconnect();
        if(networkId > 0)
        {
            wifiManager.removeNetwork(networkId);
            networkId = -1;
        }
        if(receiverRegistered)
        {
            unregisterReceiver(wifiReceiver);
            receiverRegistered = false;
        }
        return -1;
    }

    private WifiConfiguration findNetworkInExistingConfig(String s)
    {
        Iterator iterator = wifiManager.getConfiguredNetworks().iterator();
_L2:
        WifiConfiguration wificonfiguration1;
        if(!iterator.hasNext())
        {
            wificonfiguration1 = null;
        } else
        {
            WifiConfiguration wificonfiguration = (WifiConfiguration)iterator.next();
            if(!wificonfiguration.SSID.equals(s))
                continue; /* Loop/switch isn't completed */
            wificonfiguration1 = wificonfiguration;
        }
        return wificonfiguration1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private int requestNetworkChange(WifiConfiguration wificonfiguration)
    {
        statusView.setText(0x7f090245);
        return updateNetwork(wificonfiguration, false);
    }

    private int updateNetwork(WifiConfiguration wificonfiguration, boolean flag)
    {
        WifiConfiguration wificonfiguration1 = findNetworkInExistingConfig(wificonfiguration.SSID);
        wifiManager.disconnect();
        int i;
        if(wificonfiguration1 == null)
        {
            statusView.setText(0x7f090247);
        } else
        {
            statusView.setText(0x7f090248);
            if(Log.D)
                Log.d(TAG, (new StringBuilder("Removing network ")).append(wificonfiguration1.networkId).toString());
            wifiManager.removeNetwork(wificonfiguration1.networkId);
            wifiManager.saveConfiguration();
        }
        networkId = wifiManager.addNetwork(wificonfiguration);
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Inserted/Modified network ")).append(networkId).toString());
        if(networkId < 0)
            i = -1;
        else
        if(!wifiManager.enableNetwork(networkId, flag))
        {
            networkId = -1;
            i = -1;
        } else
        {
            errorCount = 0;
            wifiManager.reassociate();
            i = networkId;
        }
        return i;
    }

    void gotError()
    {
        errorCount = 1 + errorCount;
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Encountered another error.  Errorcount = ")).append(errorCount).toString());
        if(errorCount > 3)
        {
            errorCount = 0;
            doError(0x7f09024b);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        Intent intent;
        super.onCreate(bundle);
        intent = getIntent();
        if(intent != null && intent.getAction().equals("com.jindong.lib.zxing.client.android.WIFI_CONNECT")) goto _L2; else goto _L1
_L1:
        finish();
_L8:
        return;
_L2:
        String s;
        String s1;
        String s2;
        s = intent.getStringExtra("SSID");
        s1 = intent.getStringExtra("PASSWORD");
        s2 = intent.getStringExtra("TYPE");
        setContentView(0x7f030053);
        statusView = (TextView)findViewById(0x7f0c01a2);
        if(!s2.equals("WPA")) goto _L4; else goto _L3
_L3:
        NetworkType networktype = NetworkType.NETWORK_WPA;
_L6:
        wifiManager = (WifiManager)getSystemService("wifi");
        wifiManager.setWifiEnabled(true);
        wifiReceiver = new WifiReceiver(wifiManager, this, statusView, s);
        mWifiStateFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        mWifiStateFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mWifiStateFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        mWifiStateFilter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(wifiReceiver, mWifiStateFilter);
        receiverRegistered = true;
        if(s1 == null)
            s1 = "";
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Adding new configuration: \nSSID: ")).append(s).append("Type: ").append(networktype).toString());
        changeNetwork(new NetworkSetting(s, s1, networktype));
        continue; /* Loop/switch isn't completed */
_L4:
        if(s2.equals("WEP"))
        {
            networktype = NetworkType.NETWORK_WEP;
            continue; /* Loop/switch isn't completed */
        }
        if(!s2.equals("nopass"))
            break; /* Loop/switch isn't completed */
        networktype = NetworkType.NETWORK_NOPASS;
        if(true) goto _L6; else goto _L5
_L5:
        doError(0x7f09024a);
        if(true) goto _L8; else goto _L7
_L7:
    }

    protected void onDestroy()
    {
        if(wifiReceiver != null)
        {
            if(receiverRegistered)
            {
                unregisterReceiver(wifiReceiver);
                receiverRegistered = false;
            }
            wifiReceiver = null;
        }
        super.onDestroy();
    }

    public void onPause()
    {
        super.onPause();
        if(receiverRegistered)
        {
            unregisterReceiver(wifiReceiver);
            receiverRegistered = false;
        }
    }

    public void onResume()
    {
        super.onResume();
        if(wifiReceiver != null && mWifiStateFilter != null && !receiverRegistered)
        {
            registerReceiver(wifiReceiver, mWifiStateFilter);
            receiverRegistered = true;
        }
    }

    private static final int FAILURE_NO_NETWORK_ID = -1;
    private static final Pattern HEX_DIGITS_64 = Pattern.compile("[0-9A-Fa-f]{64}");
    private static final int MAX_ERROR_COUNT = 3;
    private static final String TAG = com/jindong/lib/zxing/client/android/wifi/WifiActivity.getSimpleName();
    private int errorCount;
    private IntentFilter mWifiStateFilter;
    private int networkId;
    private boolean receiverRegistered;
    private TextView statusView;
    private WifiManager wifiManager;
    private WifiReceiver wifiReceiver;

}
