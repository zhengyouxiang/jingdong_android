// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.widget.Toast;
import com.google.zxing.Result;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.camera.CameraManager;
import java.util.Vector;

// Referenced classes of package com.jindong.lib.zxing.client.android:
//            DecodeThread, ViewfinderResultPointCallback, CaptureActivity

public final class CaptureActivityHandler extends Handler
{
    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/jindong/lib/zxing/client/android/CaptureActivityHandler$State, s);
        }

        public static State[] values()
        {
            State astate[] = ENUM$VALUES;
            int i = astate.length;
            State astate1[] = new State[i];
            System.arraycopy(astate, 0, astate1, 0, i);
            return astate1;
        }

        public static final State DONE;
        private static final State ENUM$VALUES[];
        public static final State PREVIEW;
        public static final State SUCCESS;

        static 
        {
            PREVIEW = new State("PREVIEW", 0);
            SUCCESS = new State("SUCCESS", 1);
            DONE = new State("DONE", 2);
            State astate[] = new State[3];
            astate[0] = PREVIEW;
            astate[1] = SUCCESS;
            astate[2] = DONE;
            ENUM$VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    CaptureActivityHandler(CaptureActivity captureactivity, Vector vector, String s)
    {
        activity = captureactivity;
        decodeThread = new DecodeThread(captureactivity, vector, s, new ViewfinderResultPointCallback(captureactivity.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
_L2:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        sendEmptyMessage(1099);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void restartPreviewAndDecode()
    {
        if(state != State.SUCCESS)
            break MISSING_BLOCK_LABEL_48;
        state = State.PREVIEW;
        CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), 0x7f0c0001);
        CameraManager.get().requestAutoFocus(this, 0x7f0c0000);
        activity.drawViewfinder();
_L2:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        sendEmptyMessage(1099);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR lookupswitch 7: default 72
    //                   1099: 315
    //                   2131492864: 73
    //                   2131492866: 198
    //                   2131492867: 129
    //                   2131492870: 263
    //                   2131492872: 108
    //                   2131492873: 223;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return;
_L3:
        if(state == State.PREVIEW)
            try
            {
                CameraManager.get().requestAutoFocus(this, 0x7f0c0000);
            }
            catch(RuntimeException runtimeexception)
            {
                sendEmptyMessage(1099);
            }
        continue; /* Loop/switch isn't completed */
_L7:
        if(Log.D)
            Log.d(TAG, "Got restart preview message");
        restartPreviewAndDecode();
        continue; /* Loop/switch isn't completed */
_L5:
        if(Log.D)
            Log.d(TAG, "Got decode succeeded message");
        state = State.SUCCESS;
        Bundle bundle = message.getData();
        Bitmap bitmap;
        if(bundle == null)
            bitmap = null;
        else
            bitmap = (Bitmap)bundle.getParcelable("barcode_bitmap");
        activity.handleDecode((Result)message.obj, bitmap);
        continue; /* Loop/switch isn't completed */
_L4:
        state = State.PREVIEW;
        CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), 0x7f0c0001);
        continue; /* Loop/switch isn't completed */
_L8:
        if(Log.D)
            Log.d(TAG, "Got return scan result message");
        activity.setResult(-1, (Intent)message.obj);
        activity.finish();
        continue; /* Loop/switch isn't completed */
_L6:
        if(Log.D)
            Log.d(TAG, "Got product query message");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((String)message.obj));
        intent.addFlags(0x80000);
        activity.startActivity(intent);
        continue; /* Loop/switch isn't completed */
_L2:
        Toast.makeText(activity, 0x7f0901d2, 1).show();
        activity.finish();
        if(true) goto _L1; else goto _L9
_L9:
    }

    public void quitSynchronously()
    {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(decodeThread.getHandler(), 0x7f0c0007).sendToTarget();
        try
        {
            decodeThread.join();
        }
        catch(InterruptedException interruptedexception) { }
        removeMessages(0x7f0c0003);
        removeMessages(0x7f0c0002);
    }

    private static final int ERROR_PREVIEW_CAMERA = 1099;
    private static final String TAG = com/jindong/lib/zxing/client/android/CaptureActivityHandler.getSimpleName();
    private final CaptureActivity activity;
    private final DecodeThread decodeThread;
    private State state;

}
