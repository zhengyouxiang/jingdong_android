// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.app.Activity;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.jindong.app.mall.MainActivity;

public class ShortCutUtils
{

    public ShortCutUtils()
    {
    }

    public static void addShortcutToDesktop(Context context, String s, String s1, String s2, Drawable drawable, boolean flag)
    {
        Intent intent;
        String s3;
        BitmapDrawable bitmapdrawable;
        PackageManager packagemanager;
        intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        s3 = s2;
        bitmapdrawable = (BitmapDrawable)drawable;
        packagemanager = context.getPackageManager();
        BitmapDrawable bitmapdrawable1;
        android.content.pm.ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(s, 129);
        if(s3 == null)
            s3 = packagemanager.getApplicationLabel(applicationinfo).toString();
        if(bitmapdrawable != null)
            break MISSING_BLOCK_LABEL_78;
        bitmapdrawable1 = (BitmapDrawable)packagemanager.getApplicationIcon(applicationinfo);
        bitmapdrawable = bitmapdrawable1;
        intent.putExtra("android.intent.extra.shortcut.NAME", s3);
        intent.putExtra("android.intent.extra.shortcut.ICON", bitmapdrawable.getBitmap());
        intent.putExtra("duplicate", flag);
        ComponentName componentname = new ComponentName(s, (new StringBuilder(".")).append(s1).toString());
        (new Intent("android.intent.action.MAIN")).setComponent(componentname);
        intent.putExtra("android.intent.extra.shortcut.INTENT", (new Intent("android.intent.action.MAIN")).setComponent(componentname));
        context.sendBroadcast(intent);
        bitmapdrawable;
        s3;
_L2:
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        s3;
        namenotfoundexception.printStackTrace();
        bitmapdrawable;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void addShortcutToOptions(Activity activity, String s, String s1, String s2, Drawable drawable, boolean flag)
    {
        Intent intent;
        String s3;
        BitmapDrawable bitmapdrawable;
        PackageManager packagemanager;
        intent = new Intent();
        s3 = s2;
        bitmapdrawable = (BitmapDrawable)drawable;
        packagemanager = activity.getPackageManager();
        android.content.pm.ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(s, 8320);
        if(s3 == null)
            s3 = packagemanager.getApplicationLabel(applicationinfo).toString();
        if(bitmapdrawable == null)
            bitmapdrawable = (BitmapDrawable)packagemanager.getApplicationIcon(applicationinfo);
        intent.putExtra("android.intent.extra.shortcut.NAME", s3);
        intent.putExtra("android.intent.extra.shortcut.ICON", bitmapdrawable.getBitmap());
        ComponentName componentname = new ComponentName(s, s1);
        intent.putExtra("android.intent.extra.shortcut.INTENT", (new Intent("android.intent.action.MAIN")).setComponent(componentname));
        activity.setResult(-1, intent);
_L2:
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void addSortcutForJdApp(MainActivity mainactivity)
    {
        SharedPreferences sharedpreferences = mainactivity.getSharedPreferences("jdAndroidClient", 0);
        String s = mainactivity.getPackageName();
        String s1 = mainactivity.getLocalClassName();
        addShortcutToDesktop(mainactivity, s, s1, null, null, false);
        sharedpreferences.edit().putBoolean("add_short_cut_flag", true).commit();
        addShortcutToOptions(mainactivity, s, s1, null, null, false);
    }

    public static void delShortcutFromDesktop(Context context, String s, String s1, String s2)
    {
        Intent intent;
        String s3;
        PackageManager packagemanager;
        intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        s3 = s2;
        packagemanager = context.getPackageManager();
        String s4;
        android.content.pm.ApplicationInfo applicationinfo = packagemanager.getApplicationInfo(s, 8320);
        if(s3 != null)
            break MISSING_BLOCK_LABEL_54;
        s4 = packagemanager.getApplicationLabel(applicationinfo).toString();
        s3 = s4;
        intent.putExtra("android.intent.extra.shortcut.NAME", s3);
        ComponentName componentname = new ComponentName(s, s1);
        intent.putExtra("android.intent.extra.shortcut.INTENT", (new Intent("android.intent.action.MAIN")).setComponent(componentname));
        context.sendBroadcast(intent);
_L2:
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    static final String ACTION_INSTALL = "com.android.launcher.action.INSTALL_SHORTCUT";
    static final String ACTION_UNINSTALL = "com.android.launcher.action.UNINSTALL_SHORTCUT";
}
