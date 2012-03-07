// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.MyActivity;

public class AboutActivity extends MyActivity
{

    public AboutActivity()
    {
    }

    private String getSoftwareVersionName()
    {
        String s;
        try
        {
            s = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            if(Log.V)
                Log.v(TAG, "Package name not found", namenotfoundexception);
            s = null;
        }
        return s;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f0900ba);
        ((TextView)findViewById(0x7f0c0010)).setText((new StringBuilder("for android V")).append(getSoftwareVersionName()).toString());
    }

    private final String TAG = com/jindong/app/mall/more/AboutActivity.getSimpleName();
}
