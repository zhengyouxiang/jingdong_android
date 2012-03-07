// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.os.*;
import android.widget.Toast;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.camera.CameraManager;
import java.util.Hashtable;

// Referenced classes of package com.jindong.lib.zxing.client.android:
//            CaptureActivity, PlanarYUVLuminanceSource

final class DecodeHandler extends Handler
{

    DecodeHandler(CaptureActivity captureactivity, Hashtable hashtable)
    {
        multiFormatReader.setHints(hashtable);
        activity = captureactivity;
    }

    private boolean decode(byte abyte0[], int i, int j)
    {
        long l;
        Result result;
        PlanarYUVLuminanceSource planaryuvluminancesource;
        boolean flag;
        l = System.currentTimeMillis();
        result = null;
        planaryuvluminancesource = null;
        PlanarYUVLuminanceSource planaryuvluminancesource1;
        try
        {
            planaryuvluminancesource1 = CameraManager.get().buildLuminanceSource(abyte0, i, j);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            if(activity != null)
            {
                Toast.makeText(activity, 0x7f0901d3, 1).show();
                activity.finish();
            } else
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            continue; /* Loop/switch isn't completed */
        }
        planaryuvluminancesource = planaryuvluminancesource1;
_L7:
        if(planaryuvluminancesource != null) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        BinaryBitmap binarybitmap = new BinaryBitmap(new HybridBinarizer(planaryuvluminancesource));
        Result result1 = multiFormatReader.decodeWithState(binarybitmap);
        result = result1;
        multiFormatReader.reset();
_L5:
        Exception exception;
        ReaderException readerexception;
        if(result != null)
        {
            long l1 = System.currentTimeMillis();
            if(Log.D)
                Log.d(TAG, (new StringBuilder("Found barcode (")).append(l1 - l).append(" ms):\n").append(result.toString()).toString());
            Message message = Message.obtain(activity.getHandler(), 0x7f0c0003, result);
            Bundle bundle = new Bundle();
            bundle.putParcelable("barcode_bitmap", planaryuvluminancesource.renderCroppedGreyscaleBitmap());
            message.setData(bundle);
            message.sendToTarget();
        } else
        {
            Message.obtain(activity.getHandler(), 0x7f0c0002).sendToTarget();
        }
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        readerexception;
        multiFormatReader.reset();
          goto _L5
        exception;
        multiFormatReader.reset();
        throw exception;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR lookupswitch 2: default 32
    //                   2131492865: 33
    //                   2131492871: 85;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(!decode((byte[])message.obj, message.arg1, message.arg2) && activity != null)
        {
            Toast.makeText(activity, 0x7f0901d2, 1).show();
            activity.finish();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        Looper.myLooper().quit();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static final String TAG = com/jindong/lib/zxing/client/android/DecodeHandler.getSimpleName();
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();

}
