// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.app.Activity;
import android.content.DialogInterface;

public final class FinishListener
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnCancelListener, Runnable
{

    public FinishListener(Activity activity)
    {
        activityToFinish = activity;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        run();
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        run();
    }

    public void run()
    {
        activityToFinish.finish();
    }

    private final Activity activityToFinish;
}
