// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall;

import android.app.AlertDialog;
import android.app.Application;
import android.content.*;
import android.os.Process;
import com.jindong.app.mall.service.MessagePullService;
import com.jindong.app.mall.utils.CommonUtil;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.MyActivity;
import com.jindong.app.mall.utils.MyUncaughtExceptionHandler;

// Referenced classes of package com.jindong.app.mall:
//            MainActivity

public class MyApplication extends Application
{

    public MyApplication()
    {
        networkInitializationState = 0;
    }

    public static void clearCache()
    {
        if(Log.D)
            Log.d("Temp", "MyApplication clearCache() -->> ");
        SharedPreferences sharedpreferences = CommonUtil.getJdSharedPreferences();
        if(!sharedpreferences.getBoolean("remember", false))
            sharedpreferences.edit().putString("cookies", null).commit();
        Intent intent = new Intent();
        intent.setClass(getInstance(), com/jindong/app/mall/service/MessagePullService);
        intent.setAction("Action_Clear_Cache");
        getInstance().startService(intent);
    }

    public static void exit()
    {
        if(Log.D)
            Log.d("Temp", "MyApplication exit() -->> ");
        clearCache();
        if(!MessagePullService.serviceIsRunInBg())
        {
            Intent intent = new Intent();
            intent.setClass(getInstance(), com/jindong/app/mall/service/MessagePullService);
            intent.setAction("Action_Stop_Message_Service");
            getInstance().startService(intent);
        }
        killStage();
    }

    public static void exitAll()
    {
        if(Log.D)
            Log.d("Temp", "MyApplication exitAll() -->> ");
        clearCache();
        killBackground();
        killStage();
    }

    public static void exitDialog()
    {
        if(Log.D)
            Log.d("Temp", "exitDialog() -->> ");
        final boolean hasBackground = MessagePullService.serviceIsRunInBg();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("exitDialog() hasBackground -->> ")).append(hasBackground).toString());
        MainActivity mainactivity = getInstance().getMainActivity();
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(mainactivity)).create();
        alertdialog.setMessage(mainactivity.getText(0x7f0900c0));
        alertdialog.setTitle(0x7f09003b);
        android.content.DialogInterface.OnClickListener onclicklistener = new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                i;
                JVM INSTR tableswitch -3 -1: default 28
            //                           -3 48
            //                           -2 54
            //                           -1 29;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L4:
                if(hasBackground)
                    MyApplication.exitAll();
                else
                    MyApplication.exit();
                continue; /* Loop/switch isn't completed */
_L2:
                MyApplication.exit();
                continue; /* Loop/switch isn't completed */
_L3:
                dialoginterface.dismiss();
                if(true) goto _L1; else goto _L5
_L5:
            }

            private final boolean val$hasBackground;

            
            {
                hasBackground = flag;
                super();
            }
        }
;
        alertdialog.setButton(-1, mainactivity.getString(0x7f090010), onclicklistener);
        if(hasBackground)
            alertdialog.setButton(-3, mainactivity.getString(0x7f090017), onclicklistener);
        alertdialog.setButton(-2, mainactivity.getString(0x7f09000f), onclicklistener);
        alertdialog.show();
    }

    public static MyApplication getInstance()
    {
        return instance;
    }

    public static void killBackground()
    {
        if(Log.D)
            Log.d("Temp", "MyApplication killBackground() -->> ");
        Intent intent = new Intent();
        intent.setClass(getInstance(), com/jindong/app/mall/service/MessagePullService);
        intent.setAction("Action_Stop_Message_Service");
        getInstance().startService(intent);
    }

    public static void killStage()
    {
        if(Log.D)
            Log.d("Temp", "MyApplication killStage() -->> ");
        instance.setMainActivity(null);
        instance.networkInitializationState = 0;
        Process.killProcess(Process.myPid());
    }

    public MyActivity getCurrentMyActivity()
    {
        if(mainActivity == null) goto _L2; else goto _L1
_L1:
        android.app.Activity activity = mainActivity.getCurrentActivity();
        if(!(activity instanceof MyActivity)) goto _L2; else goto _L3
_L3:
        MyActivity myactivity = (MyActivity)activity;
_L5:
        return myactivity;
_L2:
        myactivity = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public MainActivity getMainActivity()
    {
        return mainActivity;
    }

    public void onCreate()
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("MyApplication onCreate() -->> Process.myPid() ")).append(Process.myPid()).toString());
        super.onCreate();
        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    public void setMainActivity(MainActivity mainactivity)
    {
        mainActivity = mainactivity;
    }

    private static MyApplication instance;
    private MainActivity mainActivity;
    public int networkInitializationState;
}
