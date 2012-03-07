// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.share;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Button;
import com.google.zxing.BarcodeFormat;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.Contents;

// Referenced classes of package com.jindong.lib.zxing.client.android.share:
//            BookmarkPickerActivity, AppPickerActivity

public final class ShareActivity extends Activity
{

    public ShareActivity()
    {
    }

    private static String massageContactData(String s)
    {
        if(s.indexOf('\n') >= 0)
            s = s.replace("\n", " ");
        if(s.indexOf('\r') >= 0)
            s = s.replace("\r", " ");
        return s;
    }

    private void showContactAsBarcode(Uri uri)
    {
        if(Log.I)
            Log.i(TAG, (new StringBuilder("Showing contact URI as barcode: ")).append(uri).toString());
        if(uri != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ContentResolver contentresolver;
        Bundle bundle;
        Cursor cursor1;
        contentresolver = getContentResolver();
        Cursor cursor = contentresolver.query(uri, null, null, null, null);
        bundle = new Bundle();
        if(cursor == null || !cursor.moveToFirst())
            continue; /* Loop/switch isn't completed */
        String s = cursor.getString(cursor.getColumnIndex("name"));
        if(s != null && s.length() > 0)
            bundle.putString("name", massageContactData(s));
        cursor.close();
        cursor1 = contentresolver.query(Uri.withAppendedPath(uri, "phones"), PHONES_PROJECTION, null, null, null);
        if(cursor1 == null) goto _L4; else goto _L3
_L3:
        int k = 0;
_L11:
        if(cursor1.moveToNext()) goto _L6; else goto _L5
_L5:
        cursor1.close();
_L4:
        Cursor cursor2 = contentresolver.query(Uri.withAppendedPath(uri, "contact_methods"), METHODS_PROJECTION, null, null, null);
        if(cursor2 == null) goto _L8; else goto _L7
_L7:
        int i;
        boolean flag;
        i = 0;
        flag = false;
_L13:
        if(cursor2.moveToNext()) goto _L10; else goto _L9
_L9:
        cursor2.close();
_L8:
        Intent intent = new Intent("com.jindong.lib.zxing.client.android.ENCODE");
        intent.addFlags(0x80000);
        intent.putExtra("ENCODE_TYPE", "CONTACT_TYPE");
        intent.putExtra("ENCODE_DATA", bundle);
        intent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
        if(Log.I)
            Log.i(TAG, (new StringBuilder("Sending bundle for encoding: ")).append(bundle).toString());
        startActivity(intent);
        continue; /* Loop/switch isn't completed */
_L6:
        String s2 = cursor1.getString(1);
        if(k < Contents.PHONE_KEYS.length)
        {
            bundle.putString(Contents.PHONE_KEYS[k], massageContactData(s2));
            k++;
        }
          goto _L11
_L10:
        int j = cursor2.getInt(1);
        String s1 = cursor2.getString(2);
        switch(j)
        {
        case 1: // '\001'
            if(i < Contents.EMAIL_KEYS.length)
            {
                bundle.putString(Contents.EMAIL_KEYS[i], massageContactData(s1));
                i++;
            }
            break;

        case 2: // '\002'
            if(!flag)
            {
                bundle.putString("postal", massageContactData(s1));
                flag = true;
            }
            break;
        }
        if(true) goto _L13; else goto _L12
_L12:
        if(true) goto _L1; else goto _L14
_L14:
    }

    private void showTextAsBarcode(String s)
    {
        if(Log.I)
            Log.i(TAG, (new StringBuilder("Showing text as barcode: ")).append(s).toString());
        if(s != null)
        {
            Intent intent = new Intent("com.jindong.lib.zxing.client.android.ENCODE");
            intent.addFlags(0x80000);
            intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
            intent.putExtra("ENCODE_DATA", s);
            intent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
            startActivity(intent);
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if(j != -1) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 33
    //                   1 46
    //                   2 33;
           goto _L2 _L3 _L4 _L3
_L2:
        return;
_L3:
        showTextAsBarcode(intent.getStringExtra("url"));
        continue; /* Loop/switch isn't completed */
_L4:
        showContactAsBarcode(intent.getData());
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030084);
        findViewById(0x7f0c02b6).setOnClickListener(contactListener);
        findViewById(0x7f0c02b5).setOnClickListener(bookmarkListener);
        findViewById(0x7f0c02b4).setOnClickListener(appListener);
        clipboardButton = (Button)findViewById(0x7f0c02b7);
        clipboardButton.setOnClickListener(clipboardListener);
    }

    protected void onResume()
    {
        super.onResume();
        if(((ClipboardManager)getSystemService("clipboard")).hasText())
        {
            clipboardButton.setEnabled(true);
            clipboardButton.setText(0x7f0901f7);
        } else
        {
            clipboardButton.setEnabled(false);
            clipboardButton.setText(0x7f0901e6);
        }
    }

    private static final int METHODS_DATA_COLUMN = 2;
    private static final int METHODS_KIND_COLUMN = 1;
    private static final String METHODS_PROJECTION[];
    private static final int PHONES_NUMBER_COLUMN = 1;
    private static final String PHONES_PROJECTION[];
    private static final int PICK_APP = 2;
    private static final int PICK_BOOKMARK = 0;
    private static final int PICK_CONTACT = 1;
    private static final String TAG = com/jindong/lib/zxing/client/android/share/ShareActivity.getSimpleName();
    private final android.view.View.OnClickListener appListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.addFlags(0x80000);
            intent.setClassName(ShareActivity.this, com/jindong/lib/zxing/client/android/share/AppPickerActivity.getName());
            startActivityForResult(intent, 2);
        }

        final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
    }
;
    private final android.view.View.OnClickListener bookmarkListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.addFlags(0x80000);
            intent.setClassName(ShareActivity.this, com/jindong/lib/zxing/client/android/share/BookmarkPickerActivity.getName());
            startActivityForResult(intent, 0);
        }

        final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
    }
;
    private Button clipboardButton;
    private final android.view.View.OnClickListener clipboardListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            ClipboardManager clipboardmanager = (ClipboardManager)getSystemService("clipboard");
            if(clipboardmanager.hasText())
            {
                Intent intent = new Intent("com.jindong.lib.zxing.client.android.ENCODE");
                intent.addFlags(0x80000);
                intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
                intent.putExtra("ENCODE_DATA", clipboardmanager.getText().toString());
                intent.putExtra("ENCODE_FORMAT", BarcodeFormat.QR_CODE.toString());
                startActivity(intent);
            }
        }

        final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
    }
;
    private final android.view.View.OnClickListener contactListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            Intent intent = new Intent("android.intent.action.PICK", android.provider.Contacts.People.CONTENT_URI);
            intent.addFlags(0x80000);
            startActivityForResult(intent, 1);
        }

        final ShareActivity this$0;

            
            {
                this$0 = ShareActivity.this;
                super();
            }
    }
;

    static 
    {
        String as[] = new String[3];
        as[0] = "_id";
        as[1] = "kind";
        as[2] = "data";
        METHODS_PROJECTION = as;
        String as1[] = new String[2];
        as1[0] = "_id";
        as1[1] = "number";
        PHONES_PROJECTION = as1;
    }
}
