// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.*;
import android.view.KeyEvent;
import android.webkit.*;
import android.widget.TextView;
import com.jindong.app.mall.utils.MyActivity;

public class HelpActivity extends MyActivity
{

    public HelpActivity()
    {
        isFirstPage = true;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 1: default 28
            //                           0 42
            //                           1 29;
                   goto _L1 _L2 _L3
_L1:
                return;
_L3:
                dialog.show();
                continue; /* Loop/switch isn't completed */
_L2:
                dialog.hide();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
        }
;
    }

    protected void onCreate(Bundle bundle)
    {
        requestWindowFeature(2);
        super.onCreate(bundle);
        Bundle bundle1 = getIntent().getExtras();
        WebSettings websettings;
        if(bundle1 == null)
            firstPage = getStringFromPreference("iphonehelpurl");
        else
            firstPage = bundle1.getString("firstPage");
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(0x7f0900db));
        setContentView(0x7f03002f);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f0900bc);
        webView = (WebView)findViewById(0x7f0c00f2);
        webView.canGoBack();
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        websettings = webView.getSettings();
        websettings.setSavePassword(false);
        websettings.setSaveFormData(false);
        websettings.setJavaScriptEnabled(true);
        websettings.setSupportZoom(true);
        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView webview, int i)
            {
                if(i == 100)
                    handler.sendEmptyMessage(0);
                super.onProgressChanged(webview, i);
            }

            final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
        }
);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView webview, String s)
            {
                webview.loadUrl(s);
                handler.sendEmptyMessage(1);
                return true;
            }

            final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
        }
);
        handler.sendEmptyMessage(1);
        webView.loadUrl(firstPage);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            if(firstPage != null && firstPage.equals(webView.getUrl()))
            {
                flag = super.onKeyDown(i, keyevent);
            } else
            {
                webView.goBack();
                flag = true;
            }
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    private static final String TAG = com/jindong/app/mall/more/HelpActivity.getCanonicalName();
    final int MSG_DIALOG_HIDE = 0;
    final int MSG_DIALOG_SHOW = 1;
    ProgressDialog dialog;
    private String firstPage;
    Handler handler;
    boolean isFirstPage;
    private WebView webView;

}
