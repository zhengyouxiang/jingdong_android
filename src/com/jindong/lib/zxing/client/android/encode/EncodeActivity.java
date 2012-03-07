// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.encode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.FinishListener;
import java.io.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.encode:
//            QRCodeEncoder

public final class EncodeActivity extends Activity
{

    public EncodeActivity()
    {
    }

    private static CharSequence makeBarcodeFileName(CharSequence charsequence)
    {
        int i = Math.min(24, charsequence.length());
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        do
        {
            if(j >= i)
                return stringbuilder;
            char c = charsequence.charAt(j);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9')
                stringbuilder.append(c);
            else
                stringbuilder.append('_');
            j++;
        } while(true);
    }

    private void showErrorMessage(int i)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(i);
        builder.setPositiveButton(0x7f0901ee, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void onCreate(Bundle bundle)
    {
        Intent intent;
        super.onCreate(bundle);
        intent = getIntent();
        if(intent == null) goto _L2; else goto _L1
_L1:
        String s = intent.getAction();
        if(!s.equals("com.jindong.lib.zxing.client.android.ENCODE") && !s.equals("android.intent.action.SEND")) goto _L2; else goto _L3
_L3:
        setContentView(0x7f03002a);
_L5:
        return;
_L2:
        finish();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, 0x7f090208).setIcon(0x1080052);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(qrCodeEncoder != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if(Log.W)
            Log.w(TAG, "No existing barcode to send?");
        flag = true;
_L3:
        return flag;
_L2:
        String s = qrCodeEncoder.getContents();
        Bitmap bitmap = QRCodeEncoder.encodeAsBitmap(s, BarcodeFormat.QR_CODE, 300, 300);
        File file;
        file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "Barcodes");
        if(file.exists() || file.mkdirs())
            break MISSING_BLOCK_LABEL_150;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Couldn't make dir ")).append(file).toString());
        showErrorMessage(0x7f090224);
        flag = true;
          goto _L3
        WriterException writerexception;
        writerexception;
        if(Log.W)
            Log.w(TAG, writerexception);
        flag = true;
          goto _L3
        File file1;
        FileOutputStream fileoutputstream;
        file1 = new File(file, (new StringBuilder()).append(makeBarcodeFileName(s)).append(".png").toString());
        file1.delete();
        fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(file1);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, fileoutputstream1);
        FileNotFoundException filenotfoundexception1;
        Exception exception;
        IOException ioexception;
        IOException ioexception1;
        Intent intent;
        FileNotFoundException filenotfoundexception2;
        if(fileoutputstream1 != null)
            try
            {
                fileoutputstream1.close();
            }
            catch(IOException ioexception2) { }
        intent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
        intent.putExtra("android.intent.extra.SUBJECT", (new StringBuilder(String.valueOf(getString(0x7f09003c)))).append(" - ").append(qrCodeEncoder.getTitle()).toString());
        intent.putExtra("android.intent.extra.TEXT", qrCodeEncoder.getContents());
        intent.putExtra("android.intent.extra.STREAM", Uri.parse((new StringBuilder("file://")).append(file1.getAbsolutePath()).toString()));
        intent.setType("image/png");
        intent.addFlags(0x80000);
        startActivity(Intent.createChooser(intent, null));
        flag = true;
          goto _L3
        filenotfoundexception2;
        filenotfoundexception1 = filenotfoundexception2;
_L6:
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Couldn't access file ")).append(file1).append(" due to ").append(filenotfoundexception1).toString());
        showErrorMessage(0x7f090224);
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception1) { }
        flag = true;
          goto _L3
        exception;
_L5:
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception) { }
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L5; else goto _L4
_L4:
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception1 = filenotfoundexception;
        fileoutputstream = fileoutputstream1;
          goto _L6
    }

    protected void onResume()
    {
        super.onResume();
        findViewById(0x7f0c00d3).getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        firstLayout = true;
    }

    private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
    private static final int SHARE_BARCODE_DIMENSION = 300;
    private static final String TAG = com/jindong/lib/zxing/client/android/encode/EncodeActivity.getSimpleName();
    private boolean firstLayout;
    private final Handler handler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 2131492868 2131492869: default 28
        //                       2131492868 81
        //                       2131492869 29;
               goto _L1 _L2 _L3
_L1:
            return;
_L3:
            Bitmap bitmap = (Bitmap)message.obj;
            ((ImageView)findViewById(0x7f0c00d4)).setImageBitmap(bitmap);
            ((TextView)findViewById(0x7f0c004e)).setText(qrCodeEncoder.getDisplayContents());
            continue; /* Loop/switch isn't completed */
_L2:
            showErrorMessage(0x7f090214);
            qrCodeEncoder = null;
            if(true) goto _L1; else goto _L4
_L4:
        }

        final EncodeActivity this$0;

            
            {
                this$0 = EncodeActivity.this;
                super();
            }
    }
;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener layoutListener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

        public void onGlobalLayout()
        {
            if(firstLayout)
            {
                View view = findViewById(0x7f0c00d3);
                int i = view.getWidth();
                int j = view.getHeight();
                int k;
                int l;
                Intent intent;
                if(i < j)
                    k = i;
                else
                    k = j;
                l = (k * 7) / 8;
                intent = getIntent();
                try
                {
                    qrCodeEncoder = new QRCodeEncoder(EncodeActivity.this, intent);
                    setTitle((new StringBuilder(String.valueOf(getString(0x7f09003c)))).append(" - ").append(qrCodeEncoder.getTitle()).toString());
                    qrCodeEncoder.requestBarcode(handler, l);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    showErrorMessage(0x7f090215);
                }
                firstLayout = false;
            }
        }

        final EncodeActivity this$0;

            
            {
                this$0 = EncodeActivity.this;
                super();
            }
    }
;
    private QRCodeEncoder qrCodeEncoder;







}
