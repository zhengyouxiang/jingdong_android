// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.camera;

import android.hardware.Camera;
import android.os.Handler;
import com.jindong.app.mall.utils.Log;

final class AutoFocusCallback
    implements android.hardware.Camera.AutoFocusCallback
{

    AutoFocusCallback()
    {
    }

    public void onAutoFocus(boolean flag, Camera camera)
    {
        if(autoFocusHandler == null) goto _L2; else goto _L1
_L1:
        android.os.Message message = autoFocusHandler.obtainMessage(autoFocusMessage, Boolean.valueOf(flag));
        autoFocusHandler.sendMessageDelayed(message, 1500L);
        autoFocusHandler = null;
_L4:
        return;
_L2:
        if(Log.D)
            Log.d(TAG, "Got auto-focus callback, but no handler for it");
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setHandler(Handler handler, int i)
    {
        autoFocusHandler = handler;
        autoFocusMessage = i;
    }

    private static final long AUTOFOCUS_INTERVAL_MS = 1500L;
    private static final String TAG = com/jindong/lib/zxing/client/android/camera/AutoFocusCallback.getSimpleName();
    private Handler autoFocusHandler;
    private int autoFocusMessage;

}
