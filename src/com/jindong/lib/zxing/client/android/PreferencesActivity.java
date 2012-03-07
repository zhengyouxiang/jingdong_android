// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import java.util.ArrayList;
import java.util.Collection;

public final class PreferencesActivity extends PreferenceActivity
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener
{

    public PreferencesActivity()
    {
    }

    private void disableLastCheckedPref()
    {
        ArrayList arraylist = new ArrayList(3);
        if(decode1D.isChecked())
            arraylist.add(decode1D);
        if(decodeQR.isChecked())
            arraylist.add(decodeQR);
        if(decodeDataMatrix.isChecked())
            arraylist.add(decodeDataMatrix);
        boolean flag;
        CheckBoxPreference acheckboxpreference[];
        int i;
        int j;
        if(arraylist.size() < 2)
            flag = true;
        else
            flag = false;
        acheckboxpreference = new CheckBoxPreference[3];
        acheckboxpreference[0] = decode1D;
        acheckboxpreference[1] = decodeQR;
        acheckboxpreference[2] = decodeDataMatrix;
        i = acheckboxpreference.length;
        j = 0;
        do
        {
            if(j >= i)
                return;
            CheckBoxPreference checkboxpreference = acheckboxpreference[j];
            boolean flag1;
            if(flag && arraylist.contains(checkboxpreference))
                flag1 = false;
            else
                flag1 = true;
            checkboxpreference.setEnabled(flag1);
            j++;
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        addPreferencesFromResource(0x7f05000c);
        PreferenceScreen preferencescreen = getPreferenceScreen();
        preferencescreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        decode1D = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_1D");
        decodeQR = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_QR");
        decodeDataMatrix = (CheckBoxPreference)preferencescreen.findPreference("preferences_decode_Data_Matrix");
        disableLastCheckedPref();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        disableLastCheckedPref();
    }

    public static final String KEY_BULK_MODE = "preferences_bulk_mode";
    public static final String KEY_COPY_TO_CLIPBOARD = "preferences_copy_to_clipboard";
    public static final String KEY_CUSTOM_PRODUCT_SEARCH = "preferences_custom_product_search";
    public static final String KEY_DECODE_1D = "preferences_decode_1D";
    public static final String KEY_DECODE_DATA_MATRIX = "preferences_decode_Data_Matrix";
    public static final String KEY_DECODE_QR = "preferences_decode_QR";
    public static final String KEY_FRONT_LIGHT = "preferences_front_light";
    public static final String KEY_HELP_VERSION_SHOWN = "preferences_help_version_shown";
    public static final String KEY_NOT_OUR_RESULTS_SHOWN = "preferences_not_out_results_shown";
    public static final String KEY_PLAY_BEEP = "preferences_play_beep";
    public static final String KEY_VIBRATE = "preferences_vibrate";
    private CheckBoxPreference decode1D;
    private CheckBoxPreference decodeDataMatrix;
    private CheckBoxPreference decodeQR;
}
