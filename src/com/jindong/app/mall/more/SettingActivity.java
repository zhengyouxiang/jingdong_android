// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import com.jindong.app.mall.service.MessagePullService;
import com.jindong.app.mall.utils.CommonUtil;

public class SettingActivity extends PreferenceActivity
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener
{

    public SettingActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getPreferenceManager().setSharedPreferencesName("jdAndroidClient");
        addPreferencesFromResource(0x7f05000b);
        PreferenceScreen preferencescreen = getPreferenceScreen();
        preferencescreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        settings = getPreferenceManager().getSharedPreferences();
        msgAutoUpdateSwitch = (CheckBoxPreference)preferencescreen.findPreference(getString(0x7f0901c5));
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        if(!s.equals(getString(0x7f0901c5))) goto _L2; else goto _L1
_L1:
        if(!msgAutoUpdateSwitch.isChecked()) goto _L4; else goto _L3
_L3:
        Intent intent = new Intent();
        intent.setClass(this, com/jindong/app/mall/service/MessagePullService);
        intent.setAction("Action_Get_Message");
        startService(intent);
_L2:
        return;
_L4:
        if(CommonUtil.getJdSharedPreferences().getBoolean("jd_widget_deleted", true))
        {
            Intent intent1 = new Intent();
            intent1.setClass(this, com/jindong/app/mall/service/MessagePullService);
            intent1.setAction("Action_Stop_Message_Service");
            startService(intent1);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    CheckBoxPreference msgAutoUpdateSwitch;
    private SharedPreferences settings;
}
