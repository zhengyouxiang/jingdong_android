// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.wifi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

final class Killer
    implements Runnable
{

    Killer(Activity activity)
    {
        parent = activity;
    }

    void launchIntent(Intent intent)
    {
        if(intent == null)
            break MISSING_BLOCK_LABEL_19;
        intent.addFlags(0x80000);
        parent.startActivity(intent);
_L1:
        return;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(parent);
        builder.setTitle(0x7f09003c);
        builder.setMessage(0x7f090218);
        builder.setPositiveButton(0x7f0901ee, null);
        builder.show();
          goto _L1
    }

    public void run()
    {
        final Handler handler = new Handler();
        (new Timer()).schedule(new TimerTask() {

            public void run()
            {
                handler.post(new Runnable() {

                    public void run()
                    {
                        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com/")));
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
            }

            final Killer this$0;
            private final Handler val$handler;


            
            {
                this$0 = Killer.this;
                handler = handler1;
                super();
            }
        }
, 3000L);
    }

    private static final long DELAY_MS = 3000L;
    private final Activity parent;
}
