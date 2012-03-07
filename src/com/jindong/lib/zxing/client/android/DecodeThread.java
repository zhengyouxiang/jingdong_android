// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package com.jindong.lib.zxing.client.android:
//            DecodeFormatManager, DecodeHandler, CaptureActivity

final class DecodeThread extends Thread
{

    DecodeThread(CaptureActivity captureactivity, Vector vector, String s, ResultPointCallback resultpointcallback)
    {
        activity = captureactivity;
        if(vector == null || vector.isEmpty())
        {
            SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(captureactivity);
            vector = new Vector();
            if(sharedpreferences.getBoolean("preferences_decode_1D", true))
                vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
            if(sharedpreferences.getBoolean("preferences_decode_QR", true))
                vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            if(sharedpreferences.getBoolean("preferences_decode_Data_Matrix", true))
                vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        if(s != null)
            hints.put(DecodeHintType.CHARACTER_SET, s);
        hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultpointcallback);
    }

    Handler getHandler()
    {
        try
        {
            handlerInitLatch.await();
        }
        catch(InterruptedException interruptedexception) { }
        return handler;
    }

    public void run()
    {
        Looper.prepare();
        handler = new DecodeHandler(activity, hints);
        handlerInitLatch.countDown();
        Looper.loop();
    }

    public static final String BARCODE_BITMAP = "barcode_bitmap";
    private final CaptureActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);
    private final Hashtable hints = new Hashtable(3);
}
