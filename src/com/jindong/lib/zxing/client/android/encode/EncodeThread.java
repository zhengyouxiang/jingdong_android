// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.encode;

import android.os.Handler;
import android.os.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.jindong.app.mall.utils.Log;

// Referenced classes of package com.jindong.lib.zxing.client.android.encode:
//            QRCodeEncoder

final class EncodeThread extends Thread
{

    EncodeThread(String s, Handler handler1, int i, BarcodeFormat barcodeformat)
    {
        contents = s;
        handler = handler1;
        pixelResolution = i;
        format = barcodeformat;
    }

    public void run()
    {
        android.graphics.Bitmap bitmap = QRCodeEncoder.encodeAsBitmap(contents, format, pixelResolution, pixelResolution);
        Message message = Message.obtain(handler, 0x7f0c0005);
        message.obj = bitmap;
        message.sendToTarget();
_L1:
        return;
        WriterException writerexception;
        writerexception;
        if(Log.E)
            Log.e(TAG, "Could not encode barcode", writerexception);
        Message.obtain(handler, 0x7f0c0004).sendToTarget();
          goto _L1
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        if(Log.E)
            Log.e(TAG, "Could not encode barcode", illegalargumentexception);
        Message.obtain(handler, 0x7f0c0004).sendToTarget();
          goto _L1
    }

    private static final String TAG = com/jindong/lib/zxing/client/android/encode/EncodeThread.getSimpleName();
    private final String contents;
    private final BarcodeFormat format;
    private final Handler handler;
    private final int pixelResolution;

}
