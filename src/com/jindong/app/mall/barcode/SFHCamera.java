// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.barcode;

import android.hardware.Camera;
import android.view.SurfaceHolder;
import com.jindong.app.mall.utils.Log;
import java.io.IOException;

public class SFHCamera
    implements android.view.SurfaceHolder.Callback
{

    public SFHCamera(SurfaceHolder surfaceholder, int i, int j, android.hardware.Camera.PreviewCallback previewcallback)
    {
        holder = null;
        mAutoFocusCallBack = new android.hardware.Camera.AutoFocusCallback() {

            public void onAutoFocus(boolean flag, Camera camera)
            {
                if(flag)
                    mCamera.setOneShotPreviewCallback(previewCallback);
            }

            final SFHCamera this$0;

            
            {
                this$0 = SFHCamera.this;
                super();
            }
        }
;
        holder = surfaceholder;
        holder.addCallback(this);
        holder.setType(3);
        width = i;
        height = j;
        previewCallback = previewcallback;
    }

    public void AutoFocusAndPreviewCallback()
    {
        if(mCamera != null)
            mCamera.autoFocus(mAutoFocusCallBack);
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        android.hardware.Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(width, height);
        parameters.setPictureFormat(256);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        if(Log.V)
            Log.v("Camera", "surfaceChanged");
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        mCamera = Camera.open();
        mCamera.setPreviewDisplay(holder);
        if(Log.V)
            Log.v("Camera", "surfaceCreated");
_L1:
        return;
        IOException ioexception;
        ioexception;
        mCamera.release();
        mCamera = null;
          goto _L1
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera = null;
        if(Log.V)
            Log.v("Camera", "surfaceDestroyed");
    }

    private int height;
    private SurfaceHolder holder;
    private android.hardware.Camera.AutoFocusCallback mAutoFocusCallBack;
    private Camera mCamera;
    private android.hardware.Camera.PreviewCallback previewCallback;
    private int width;


}
