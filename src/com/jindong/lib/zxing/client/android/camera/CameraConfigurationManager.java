// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.jindong.app.mall.utils.Log;
import java.util.regex.Pattern;

// Referenced classes of package com.jindong.lib.zxing.client.android.camera:
//            CameraManager

final class CameraConfigurationManager
{

    CameraConfigurationManager(Context context1)
    {
        context = context1;
    }

    private static int findBestMotZoomValue(CharSequence charsequence, int i)
    {
        int j;
        String as[];
        int k;
        int l;
        j = 0;
        as = COMMA_PATTERN.split(charsequence);
        k = as.length;
        l = 0;
_L3:
        if(l < k) goto _L2; else goto _L1
_L1:
        int i1 = j;
_L4:
        return i1;
_L2:
        String s = as[l].trim();
        double d = Double.parseDouble(s);
        int j1 = (int)(10D * d);
        if(Math.abs((double)i - d) < (double)Math.abs(i - j))
            j = j1;
        l++;
          goto _L3
        NumberFormatException numberformatexception;
        numberformatexception;
        i1 = i;
          goto _L4
    }

    private static Point findBestPreviewSizeValue(CharSequence charsequence, Point point)
    {
        String as[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        as = COMMA_PATTERN.split(charsequence);
        i = as.length;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0x7fffffff;
_L5:
        if(j < i) goto _L2; else goto _L1
_L1:
        int j2 = l;
_L6:
        String s;
        int j1;
        NumberFormatException numberformatexception;
        int k1;
        int l1;
        int i2;
        Point point1;
        if(k > 0 && j2 > 0)
            point1 = new Point(k, j2);
        else
            point1 = null;
        return point1;
_L2:
        s = as[j].trim();
        j1 = s.indexOf('x');
        if(j1 >= 0) goto _L4; else goto _L3
_L3:
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Bad preview-size: ")).append(s).toString());
_L7:
        j++;
          goto _L5
_L4:
        k1 = Integer.parseInt(s.substring(0, j1));
        l1 = Integer.parseInt(s.substring(j1 + 1));
        i2 = Math.abs(k1 - point.x) + Math.abs(l1 - point.y);
        if(i2 != 0)
            break MISSING_BLOCK_LABEL_218;
        k = k1;
        j2 = l1;
          goto _L6
        numberformatexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Bad preview-size: ")).append(s).toString());
          goto _L7
        if(i2 < i1)
        {
            k = k1;
            l = l1;
            i1 = i2;
        }
          goto _L7
    }

    private static Point getCameraResolution(android.hardware.Camera.Parameters parameters, Point point)
    {
        String s = parameters.get("preview-size-values");
        if(s == null)
            s = parameters.get("preview-size-value");
        Point point1 = null;
        if(s != null)
        {
            if(Log.D)
                Log.d(TAG, (new StringBuilder("preview-size-values parameter: ")).append(s).toString());
            point1 = findBestPreviewSizeValue(s, point);
        }
        if(point1 == null)
            point1 = new Point((point.x >> 3) << 3, (point.y >> 3) << 3);
        return point1;
    }

    private void setFlash(android.hardware.Camera.Parameters parameters)
    {
        if(Build.MODEL.contains("Behold II") && CameraManager.SDK_INT == 3)
            parameters.set("flash-value", 1);
        else
            parameters.set("flash-value", 2);
        parameters.set("flash-mode", "off");
    }

    private void setZoom(android.hardware.Camera.Parameters parameters)
    {
        String s = parameters.get("zoom-supported");
        if(s == null || Boolean.parseBoolean(s)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        String s1;
        i = 27;
        s1 = parameters.get("max-zoom");
        if(s1 == null)
            break MISSING_BLOCK_LABEL_60;
        double d = Double.parseDouble(s1);
        int i1 = (int)(10D * d);
        if(i > i1)
            i = i1;
_L3:
        String s2;
        s2 = parameters.get("taking-picture-zoom-max");
        if(s2 == null)
            break MISSING_BLOCK_LABEL_89;
        int l = Integer.parseInt(s2);
        if(i > l)
            i = l;
_L4:
        String s3;
        String s4;
        s3 = parameters.get("mot-zoom-values");
        if(s3 != null)
            i = findBestMotZoomValue(s3, i);
        s4 = parameters.get("mot-zoom-step");
        if(s4 == null)
            break MISSING_BLOCK_LABEL_154;
        int k;
        int j = (int)(10D * Double.parseDouble(s4.trim()));
        if(j <= 1)
            break MISSING_BLOCK_LABEL_154;
        k = i % j;
        i -= k;
_L5:
        if(s1 != null || s3 != null)
            parameters.set("zoom", String.valueOf((double)i / 10D));
        if(s2 != null)
            parameters.set("taking-picture-zoom", i);
          goto _L1
        NumberFormatException numberformatexception2;
        numberformatexception2;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Bad max-zoom: ")).append(s1).toString());
          goto _L3
        NumberFormatException numberformatexception1;
        numberformatexception1;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Bad taking-picture-zoom-max: ")).append(s2).toString());
          goto _L4
        NumberFormatException numberformatexception;
        numberformatexception;
          goto _L5
    }

    Point getCameraResolution()
    {
        return cameraResolution;
    }

    int getPreviewFormat()
    {
        return previewFormat;
    }

    String getPreviewFormatString()
    {
        return previewFormatString;
    }

    Point getScreenResolution()
    {
        if(screenResolution == null && context != null)
        {
            Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            screenResolution = new Point(display.getWidth(), display.getHeight());
        }
        return screenResolution;
    }

    void initFromCameraParameters(Camera camera)
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        previewFormat = parameters.getPreviewFormat();
        previewFormatString = parameters.get("preview-format");
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Default preview format: ")).append(previewFormat).append('/').append(previewFormatString).toString());
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        screenResolution = new Point(display.getWidth(), display.getHeight());
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Screen resolution: ")).append(screenResolution).toString());
        cameraResolution = getCameraResolution(parameters, screenResolution);
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Camera resolution: ")).append(screenResolution).toString());
    }

    void setDesiredCameraParameters(Camera camera)
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        if(Log.D)
            Log.d(TAG, (new StringBuilder("Setting preview size: ")).append(cameraResolution).toString());
        parameters.setPreviewSize(cameraResolution.x, cameraResolution.y);
        setFlash(parameters);
        setZoom(parameters);
        camera.setParameters(parameters);
    }

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final int DESIRED_SHARPNESS = 30;
    private static final String TAG = com/jindong/lib/zxing/client/android/camera/CameraConfigurationManager.getSimpleName();
    private static final int TEN_DESIRED_ZOOM = 27;
    private Point cameraResolution;
    private final Context context;
    private int previewFormat;
    private String previewFormatString;
    private Point screenResolution;

}
