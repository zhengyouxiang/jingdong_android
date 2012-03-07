// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.utils.MyActivity;

public class BarcodeInputActivity extends MyActivity
{

    public BarcodeInputActivity()
    {
    }

    private void findView()
    {
        contentEdit = (EditText)findViewById(0x7f0c0029);
        okButton = (Button)findViewById(0x7f0c002a);
        cancelButton = (Button)findViewById(0x7f0c002b);
    }

    private void init()
    {
        okButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                submit();
            }

            final BarcodeInputActivity this$0;

            
            {
                this$0 = BarcodeInputActivity.this;
                super();
            }
        }
);
        cancelButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

            final BarcodeInputActivity this$0;

            
            {
                this$0 = BarcodeInputActivity.this;
                super();
            }
        }
);
    }

    private void submit()
    {
        String s = (new StringBuilder()).append(contentEdit.getText()).toString();
        if("".equals(s))
            Toast.makeText(this, 0x7f09002e, 0).show();
        else
        if(s.length() < 6)
        {
            Toast.makeText(this, 0x7f09002f, 0).show();
        } else
        {
            Intent intent = getIntent();
            intent.putExtra("SCAN_RESULT", (new StringBuilder()).append(s).toString());
            intent.putExtra("SCAN_RESULT_FORMAT", "input");
            setResult(-1, intent);
            finish();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030009);
        findView();
        init();
        ((TextView)findViewById(0x7f0c02c7)).setText("\u8F93\u5165\u6761\u7801");
    }

    protected void onResume()
    {
        super.onResume();
        post(new Runnable() {

            public void run()
            {
                ((InputMethodManager)getApplicationContext().getSystemService("input_method")).toggleSoftInput(0, 2);
            }

            final BarcodeInputActivity this$0;

            
            {
                this$0 = BarcodeInputActivity.this;
                super();
            }
        }
, 100);
    }

    private Button cancelButton;
    private EditText contentEdit;
    private Button okButton;

}
