// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.open;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jindong.app.mall.utils.Log;

public class InterfaceActivity extends Activity
{

    public InterfaceActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        if(Log.D)
            Log.d("Temp", "InterfaceActivity onCreate() -->> ");
        super.onCreate(bundle);
        com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command command = com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command.createCommand(getIntent());
        Intent intent = new Intent("com.360buy.interfaceBroadcastReceiver");
        intent.putExtras(command.getBundle());
        sendBroadcast(intent);
        finish();
    }
}
