// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.wifi;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.*;
import android.widget.TextView;
import com.jindong.app.mall.utils.Log;

// Referenced classes of package com.jindong.lib.zxing.client.android.wifi:
//            WifiActivity, Killer

final class WifiReceiver extends BroadcastReceiver
{

    WifiReceiver(WifiManager wifimanager, WifiActivity wifiactivity, TextView textview, String s)
    {
        parent = wifiactivity;
        statusView = textview;
        mWifiManager = wifimanager;
    }

    private void handleChange(SupplicantState supplicantstate, boolean flag)
    {
        if(flag || supplicantstate == SupplicantState.INACTIVE)
        {
            if(Log.D)
                Log.d(TAG, "Found an error");
            parent.gotError();
        }
    }

    private void handleNetworkStateChanged(NetworkInfo networkinfo)
    {
        if(networkinfo.getDetailedState() == android.net.NetworkInfo.DetailedState.FAILED)
        {
            if(Log.D)
                Log.d(TAG, "Detailed Network state failed");
            parent.gotError();
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        if(!intent.getAction().equals("android.net.wifi.supplicant.STATE_CHANGE")) goto _L2; else goto _L1
_L1:
        handleChange((SupplicantState)intent.getParcelableExtra("newState"), intent.hasExtra("supplicantError"));
_L4:
        return;
_L2:
        if(!intent.getAction().equals("android.net.wifi.STATE_CHANGE"))
            break; /* Loop/switch isn't completed */
        handleNetworkStateChanged((NetworkInfo)intent.getParcelableExtra("networkInfo"));
        if(true) goto _L4; else goto _L3
_L3:
        if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
        {
            NetworkInfo anetworkinfo[] = ((ConnectivityManager)parent.getSystemService("connectivity")).getAllNetworkInfo();
            int i = anetworkinfo.length;
            int j = 0;
            while(j < i) 
            {
                NetworkInfo networkinfo = anetworkinfo[j];
                if(networkinfo.getTypeName().contentEquals("WIFI"))
                {
                    android.net.NetworkInfo.State state = networkinfo.getState();
                    String s = mWifiManager.getConnectionInfo().getSSID();
                    if(state == android.net.NetworkInfo.State.CONNECTED && s != null)
                    {
                        mWifiManager.saveConfiguration();
                        String s1 = parent.getString(0x7f090246);
                        statusView.setText((new StringBuilder(String.valueOf(s1))).append('\n').append(s).toString());
                        (new Killer(parent)).run();
                    }
                    if(state == android.net.NetworkInfo.State.DISCONNECTED)
                    {
                        if(Log.D)
                            Log.d(TAG, (new StringBuilder("Got state: ")).append(state).append(" for ssid: ").append(s).toString());
                        parent.gotError();
                    }
                }
                j++;
            }
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    private static final String TAG = com/jindong/lib/zxing/client/android/wifi/WifiReceiver.getSimpleName();
    private final WifiManager mWifiManager;
    private final WifiActivity parent;
    private final TextView statusView;

}
