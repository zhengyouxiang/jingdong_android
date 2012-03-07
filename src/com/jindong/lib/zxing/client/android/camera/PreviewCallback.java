// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import com.jindong.app.mall.utils.Log;

// Referenced classes of package com.jindong.lib.zxing.client.android.camera:
//            CameraConfigurationManager

final class PreviewCallback
    implements android.hardware.Camera.PreviewCallback
{

    PreviewCallback(CameraConfigurationManager cameraconfigurationmanager, boolean flag)
    {
        configManager = cameraconfigurationmanager;
        useOneShotPreviewCallback = flag;
    }

    public void onPreviewFrame(byte abyte0[], Camera camera)
    {
        Point point;
        point = configManager.getCameraResolution();
        if(!useOneShotPreviewCallback)
            camera.setPreviewCallback(null);
        if(previewHandler == null) goto _L2; else goto _L1
_L1:
        previewHandler.obtainMessage(previewMessage, point.x, point.y, abyte0).sendToTarget();
        previewHandler = null;
_L4:
        return;
_L2:
        if(Log.D)
            Log.d(TAG, "Got preview callback, but no handler for it");
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setHandler(Handler handler, int i)
    {
        previewHandler = handler;
        previewMessage = i;
    }

    private static final String TAG = com/jindong/lib/zxing/client/android/camera/PreviewCallback.getSimpleName();
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;
    private final boolean useOneShotPreviewCallback;

}
