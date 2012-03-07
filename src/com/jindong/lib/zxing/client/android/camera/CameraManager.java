// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.PlanarYUVLuminanceSource;
import java.io.IOException;

// Referenced classes of package com.jindong.lib.zxing.client.android.camera:
//            CameraConfigurationManager, PreviewCallback, AutoFocusCallback, FlashlightManager

public final class CameraManager
{

    private CameraManager(Context context1)
    {
        context = context1;
        configManager = new CameraConfigurationManager(context1);
        boolean flag;
        if(Integer.parseInt(android.os.Build.VERSION.SDK) > 3)
            flag = true;
        else
            flag = false;
        useOneShotPreviewCallback = flag;
        previewCallback = new PreviewCallback(configManager, useOneShotPreviewCallback);
    }

    public static CameraManager get()
    {
        return cameraManager;
    }

    public static void init(Context context1)
    {
        if(cameraManager == null)
            cameraManager = new CameraManager(context1);
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte abyte0[], int i, int j)
    {
        Rect rect = getFramingRectInPreview();
        if(rect != null) goto _L2; else goto _L1
_L1:
        PlanarYUVLuminanceSource planaryuvluminancesource = null;
_L4:
        return planaryuvluminancesource;
_L2:
        int k = configManager.getPreviewFormat();
        String s = configManager.getPreviewFormatString();
        switch(k)
        {
        default:
            if("yuv420p".equals(s))
                planaryuvluminancesource = new PlanarYUVLuminanceSource(abyte0, i, j, rect.left, rect.top, rect.width(), rect.height());
            else
                throw new IllegalArgumentException((new StringBuilder("Unsupported picture format: ")).append(k).append('/').append(s).toString());
            break;

        case 16: // '\020'
        case 17: // '\021'
            planaryuvluminancesource = new PlanarYUVLuminanceSource(abyte0, i, j, rect.left, rect.top, rect.width(), rect.height());
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void closeDriver()
    {
        if(camera != null)
        {
            FlashlightManager.disableFlashlight();
            camera.release();
            camera = null;
        }
    }

    public Rect getFramingRect()
    {
        Point point = configManager.getScreenResolution();
        if(point != null) goto _L2; else goto _L1
_L1:
        Rect rect = null;
_L4:
        return rect;
_L2:
        if(framingRect == null)
        {
            if(camera == null)
            {
                rect = null;
                continue; /* Loop/switch isn't completed */
            }
            int i = (3 * point.x) / 4;
            int j;
            int k;
            int l;
            if(i < 240)
                i = 240;
            else
            if(i > 480)
                i = 480;
            j = (3 * point.y) / 4;
            if(j < 240)
                j = 240;
            else
            if(j > 360)
                j = 360;
            k = (point.x - i) / 2;
            l = (point.y - j) / 2;
            framingRect = new Rect(k, l, k + i, l + j);
            if(Log.D)
                Log.d(TAG, (new StringBuilder("Calculated framing rect: ")).append(framingRect).toString());
        }
        rect = framingRect;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Rect getFramingRectInPreview()
    {
        Rect rect = getFramingRect();
        Rect rect2;
        if(rect == null)
        {
            rect2 = null;
        } else
        {
            if(framingRectInPreview == null)
            {
                Rect rect1 = new Rect(rect);
                Point point = configManager.getCameraResolution();
                Point point1 = configManager.getScreenResolution();
                rect1.left = (rect1.left * point.x) / point1.x;
                rect1.right = (rect1.right * point.x) / point1.x;
                rect1.top = (rect1.top * point.y) / point1.y;
                rect1.bottom = (rect1.bottom * point.y) / point1.y;
                framingRectInPreview = rect1;
            }
            rect2 = framingRectInPreview;
        }
        return rect2;
    }

    public void openDriver(SurfaceHolder surfaceholder)
        throws IOException
    {
        if(camera == null)
        {
            camera = Camera.open();
            if(camera == null)
                throw new IOException();
            camera.setPreviewDisplay(surfaceholder);
            if(!initialized)
            {
                initialized = true;
                configManager.initFromCameraParameters(camera);
            }
            configManager.setDesiredCameraParameters(camera);
            if(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("preferences_front_light", false))
                FlashlightManager.enableFlashlight();
        }
    }

    public void requestAutoFocus(Handler handler, int i)
    {
        if(camera != null && previewing)
        {
            autoFocusCallback.setHandler(handler, i);
            camera.autoFocus(autoFocusCallback);
        }
    }

    public void requestPreviewFrame(Handler handler, int i)
    {
        if(camera != null && previewing)
        {
            previewCallback.setHandler(handler, i);
            if(useOneShotPreviewCallback)
                camera.setOneShotPreviewCallback(previewCallback);
            else
                camera.setPreviewCallback(previewCallback);
        }
    }

    public void startPreview()
    {
        if(camera != null && !previewing)
        {
            camera.startPreview();
            previewing = true;
        }
    }

    public void stopPreview()
    {
        if(camera != null && previewing)
        {
            if(!useOneShotPreviewCallback)
                camera.setPreviewCallback(null);
            camera.stopPreview();
            previewCallback.setHandler(null, 0);
            autoFocusCallback.setHandler(null, 0);
            previewing = false;
        }
    }

    private static final int MAX_FRAME_HEIGHT = 360;
    private static final int MAX_FRAME_WIDTH = 480;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    static final int SDK_INT;
    private static final String TAG = com/jindong/lib/zxing/client/android/camera/CameraManager.getSimpleName();
    private static CameraManager cameraManager;
    private final AutoFocusCallback autoFocusCallback = new AutoFocusCallback();
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private final boolean useOneShotPreviewCallback;

    static 
    {
        int j = Integer.parseInt(android.os.Build.VERSION.SDK);
        int i = j;
_L2:
        SDK_INT = i;
        return;
        NumberFormatException numberformatexception;
        numberformatexception;
        i = 10000;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
